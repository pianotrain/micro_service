package com.rensm.audit.service.mq;


import com.rensm.audit.service.mq.rocketmq.RocketMqConsumerBuilder;
import com.rensm.audit.service.mq.rocketmq.RocketMqProducerBuilder;

public class MQ {
    public MQ() {
    }

    public static class RocketMQ {
        public RocketMQ() {
        }

        public static RocketMqProducerBuilder producer() {
            return RocketMqProducerBuilder.builder();
        }

        public static RocketMqConsumerBuilder consumer() {
            return RocketMqConsumerBuilder.builder();
        }
    }
}

