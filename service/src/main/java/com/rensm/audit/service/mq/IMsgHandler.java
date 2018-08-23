package com.rensm.audit.service.mq;

public interface IMsgHandler {
    MsgHandleStatus process(String var1);

    public static enum MsgHandleStatus {
        SUCCESS,
        FAIL;

        private MsgHandleStatus() {
        }
    }
}
