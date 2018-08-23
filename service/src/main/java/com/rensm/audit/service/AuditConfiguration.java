package com.rensm.audit.service;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.rensm.audit.service.mq.IMsgHandler;
import com.rensm.audit.service.mq.rocketmq.RocketMqConsumer;
import com.rensm.audit.service.mq.rocketmq.RocketMqConsumerBuilder;
import com.rensm.audit.service.mq.rocketmq.RocketMqProducer;
import com.rensm.audit.service.mq.rocketmq.RocketMqProducerBuilder;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class AuditConfiguration {

    @Bean
    public RocketMqConsumer auditConsumer() throws MQClientException {

        IMsgHandler handler = new AuditMessageHandler();

        RocketMqConsumerBuilder builder =
                RocketMqConsumerBuilder.builder()
                        .nameServer("192.168.6.126:9876")
                        .instanceName("aut_consumer01")
                        .subscribe("aut", "*")
                        .groupName("aut_consumer")
                        .registerMsgHandler(handler);

        return new RocketMqConsumer(builder);
    }

    @Bean
    public RocketMqProducer testProducer() throws MQClientException {

        RocketMqProducerBuilder builder =
                RocketMqProducerBuilder.builder()
                        .nameServer("192.168.6.126:9876")
                        .instanceName("aut_test_producer02")
                        .groupName("aut_test_producer");

        return new RocketMqProducer(builder);
    }

    @Bean
    public TestConfig testConfig(){

        return new TestConfig();
    }
}
