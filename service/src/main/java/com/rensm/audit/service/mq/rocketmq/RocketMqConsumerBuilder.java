package com.rensm.audit.service.mq.rocketmq;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.rensm.audit.service.mq.IMsgHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class RocketMqConsumerBuilder {
    private String groupName = "";
    private String nameServer = "";
    private String instanceName = "";
    private static final String DEFAULT_TAGS = "*";
    private Map<String, String> topicsAndTags = new LinkedHashMap();
    private MessageListener listener;
    private IMsgHandler handler;

    public RocketMqConsumerBuilder() {
    }

    public static RocketMqConsumerBuilder builder() {
        return new RocketMqConsumerBuilder();
    }

    public RocketMqConsumerBuilder groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public RocketMqConsumerBuilder nameServer(String nameServer) {
        this.nameServer = nameServer;
        return this;
    }

    public RocketMqConsumerBuilder instanceName(String instanceName) {
        this.instanceName = instanceName;
        return this;
    }

    public RocketMqConsumer build() throws MQClientException {
        return new RocketMqConsumer(this);
    }

    public RocketMqConsumerBuilder subscribe(String topic, String tags) {
        this.topicsAndTags.put(topic, tags);
        return this;
    }

    public RocketMqConsumerBuilder subscribe(String topic) {
        this.topicsAndTags.put(topic, "*");
        return this;
    }

    public RocketMqConsumerBuilder registerMsgListener(MessageListener listener) {
        this.listener = listener;
        return this;
    }

    public RocketMqConsumerBuilder registerMsgHandler(IMsgHandler handler) {
        this.handler = handler;
        return this;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getNameServer() {
        return this.nameServer;
    }

    public String getInstanceName() {
        return this.instanceName;
    }

    public Map<String, String> getTopicsAndTags() {
        return this.topicsAndTags;
    }

    public MessageListener getListener() {
        return this.listener;
    }

    public boolean useCustomMessageListener() {
        return null != this.listener;
    }

    public IMsgHandler getHandler() {
        return this.handler;
    }
}

