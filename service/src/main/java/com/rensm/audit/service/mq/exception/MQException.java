package com.rensm.audit.service.mq.exception;

/**
 * Created by DELL on 2018/8/14.
 */
public class MQException extends Exception {
    public MQException(String errorMessage) {
        super(errorMessage);
    }

    public MQException(String errorMessage, Throwable t) {
        super(errorMessage, t);
    }
}
