package com.rensm.audit.service.mq;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

public class Message {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private String id;
    private String topic;
    private String tag;
    private String key;
    private String body;

    public String getTopic() {
        return this.topic;
    }

    public String getTag() {
        return this.tag;
    }

    public String getKey() {
        return this.key;
    }

    public String getBody() {
        return this.body;
    }

    public Message(MessageBuilder builder) {
        this.id = builder.getId();
        this.topic = builder.getTopic();
        this.tag = builder.getTag();
        this.key = builder.getKey();
        this.body = builder.getBody();
    }

    public String toString() {
        return MessageFormat.format("{topic:{0}, tag:{1}, key:{2}, body:{3} }", new Object[]{this.topic, this.tag, this.key, this.body});
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getBodyBytes() {
        if(null != this.body) {
            try {
                return this.body.getBytes("UTF-8");
            } catch (UnsupportedEncodingException var2) {
                return null;
            }
        } else {
            return null;
        }
    }
}

