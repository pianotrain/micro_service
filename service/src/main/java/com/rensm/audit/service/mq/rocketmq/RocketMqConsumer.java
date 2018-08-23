package com.rensm.audit.service.mq.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.hook.ConsumeMessageContext;
import com.alibaba.rocketmq.client.hook.ConsumeMessageHook;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.rensm.audit.service.mq.IMsgConsumer;
import com.rensm.audit.service.mq.IMsgHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class RocketMqConsumer implements IMsgConsumer {
    private static final Logger logger = LoggerFactory.getLogger(RocketMqConsumer.class);

    public RocketMqConsumer(final RocketMqConsumerBuilder builder) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup(builder.getGroupName());
        consumer.setNamesrvAddr(builder.getNameServer());
        consumer.setInstanceName(builder.getInstanceName());
        consumer.setMessageListener(builder.getListener());
        consumer.getDefaultMQPushConsumerImpl().registerConsumeMessageHook(new ConsumeMessageHook() {
            public String hookName() {
                return null;
            }

            public void consumeMessageBefore(ConsumeMessageContext context) {
            }

            public void consumeMessageAfter(ConsumeMessageContext context) {
            }
        });
        Iterator var3 = builder.getTopicsAndTags().entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)var3.next();
            consumer.subscribe((String)entry.getKey(), (String)entry.getValue());
        }

        if(!builder.useCustomMessageListener()) {
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    logger.info(Thread.currentThread().getName() + " Receive New Messages: " + msgs.size());
                    MessageExt msg = (MessageExt)msgs.get(0);
                    String message = new String(msg.getBody());
                    logger.info("Processing message {}...", msg.getMsgId());
                    IMsgHandler.MsgHandleStatus status = builder.getHandler().process(message);
                    logger.info("Processed message {}, result is {}.", msg.getMsgId(), status.name());
                    ConsumeConcurrentlyStatus result = status == IMsgHandler.MsgHandleStatus.SUCCESS?ConsumeConcurrentlyStatus.CONSUME_SUCCESS:ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    logger.info("Messages are handled, final result is {}.", result.name());
                    return result;
                }
            });
        }

        consumer.start();
    }
}

