package com.rensm.audit.service.controller;

import com.mongodb.MongoClient;
import com.rensm.audit.service.TestConfig;
import com.rensm.audit.service.mq.Message;
import com.rensm.audit.service.mq.MessageBuilder;
import com.rensm.audit.service.mq.rocketmq.RocketMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RocketMqProducer testProducer;

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private TestConfig config;

    @Autowired
    private MongoClient mongo;

    @RequestMapping("/test")
    public void test(@RequestParam("key") String key, @RequestParam("value") String value){

        template.opsForValue().set(key, value);
    }

    @RequestMapping("/testmq")
    public void testmq(){

        Message message = new Message(MessageBuilder.builder().topic("aut").tag("*").key("test101").body("World"));

        testProducer.send(message);
    }

    @RequestMapping("/testconfig")
    public boolean testConfig(){

        return config.isRedisEnable();
    }

}
