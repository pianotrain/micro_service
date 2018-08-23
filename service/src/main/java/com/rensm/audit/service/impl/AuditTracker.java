package com.rensm.audit.service.impl;

import com.rensm.api.IAuditTracker;
import com.rensm.api.exception.InternalServiceException;
import com.rensm.api.model.RecordItem;
import com.rensm.audit.service.aspect.annotation.LogAnnotation;
import com.rensm.audit.service.domain.dao.AuditRecordDao;
import com.rensm.audit.service.domain.entity.AuditRecord;
import com.rensm.audit.service.domain.entity.IEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AuditTracker implements IAuditTracker {

    private static final Logger logger = LoggerFactory.getLogger(AuditTracker.class);

    @Autowired
    private AuditRecordDao repository;

    public boolean write(RecordItem item) throws InternalServiceException {



        AuditRecord record = new AuditRecord(item);
        return 0 < repository.insert(record);
    }

    public List<RecordItem> getRecordByOperation(String operation) throws InternalServiceException {


        List<AuditRecord> auditRecords = repository.findByOperation(operation);

        return convertToDto(auditRecords);
    }

    @LogAnnotation
    public List<RecordItem> getRecordBySource(String source) throws InternalServiceException {


        List<AuditRecord> auditRecords = repository.findBySource(source);

        return convertToDto(auditRecords);
    }

    private List<RecordItem> convertToDto(List<AuditRecord> auditRecords) {

        List<RecordItem> recordItems = new ArrayList<RecordItem>();
        for(IEntity<RecordItem> record : auditRecords){

            recordItems.add(record.toDto());
        }

        return recordItems;
    }

//    @Autowired
//    private AuditRecordDao repository;
//
//    public boolean verifyCard(String cardNumber) throws InternalServiceException {
//
//        Card card = repository.findByCardNumber(cardNumber);
//
//        return null != card ? true : false;
//    }
}
