package com.rensm.audit.service.mq;

public class MsgSendResult {
    private String msgId;
    private String sendStatus;

    public MsgSendResult() {
    }

    public MsgSendResult(String msgId, String sendStatus) {
        this.msgId = msgId;
        this.sendStatus = sendStatus;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getSendStatus() {
        return this.sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
}
