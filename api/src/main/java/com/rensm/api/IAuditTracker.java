package com.rensm.api;

import com.rensm.api.exception.InternalServiceException;
import com.rensm.api.model.RecordItem;

import java.util.List;

public interface IAuditTracker {

    boolean write(RecordItem item) throws InternalServiceException;
    List<RecordItem> getRecordByOperation(String operation) throws InternalServiceException;
    List<RecordItem> getRecordBySource(String source) throws InternalServiceException;
}
