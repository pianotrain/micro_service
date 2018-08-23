package com.rensm.audit.service;

import com.rensm.audit.service.mq.IMsgHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditMessageHandler implements IMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuditMessageHandler.class);

    @Override
    public MsgHandleStatus process(String message) {

        logger.info("Receive message '{}'", message);

        return MsgHandleStatus.SUCCESS;
    }
}
