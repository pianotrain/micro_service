package com.rensm.audit.service;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

public class AuditMessageListener implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

        System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + list.size());

        MessageExt msg = list.get(0);
        String body = new String(msg.getBody());

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //: ConsumeConcurrentlyStatus.RECONSUME_LATER;
    }
}
