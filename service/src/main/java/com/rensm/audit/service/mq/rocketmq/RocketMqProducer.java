package com.rensm.audit.service.mq.rocketmq;


import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.rensm.audit.service.mq.IMsgProducer;
import com.rensm.audit.service.mq.Message;
import com.rensm.audit.service.mq.MsgSendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RocketMqProducer implements IMsgProducer {
    private static final Logger logger = LoggerFactory.getLogger(RocketMqProducer.class);
    private DefaultMQProducer producer = new DefaultMQProducer();

    public MsgSendResult send(Message msg) {
        com.alibaba.rocketmq.common.message.Message message = new com.alibaba.rocketmq.common.message.Message(msg.getTopic(), msg.getTag(), msg.getKey(), msg.getBody().getBytes());
        SendResult result = null;

        try {
            result = this.producer.send(message);
        } catch (RemotingException | MQBrokerException | InterruptedException | MQClientException var5) {
            var5.printStackTrace();
            logger.error("Error while sending message.", var5);
        }

        MsgSendResult sendResult = new MsgSendResult();
        sendResult.setMsgId(result.getMsgId());
        sendResult.setSendStatus(result.getSendStatus().name());
        logger.info("Message is send successfully to topic {}, id is {}", msg.getTopic(), result.getMsgId());
        return sendResult;
    }

    public RocketMqProducer(RocketMqProducerBuilder builder) throws MQClientException {
        this.producer.setNamesrvAddr(builder.getNameServer());
        this.producer.setProducerGroup(builder.getGroupName());
        this.producer.setInstanceName(builder.getInstanceName());
        this.producer.start();
    }
}

