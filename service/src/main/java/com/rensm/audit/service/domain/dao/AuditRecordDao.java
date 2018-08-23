package com.rensm.audit.service.domain.dao;

import com.rensm.audit.service.domain.entity.AuditRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuditRecordDao {

    Integer insert(AuditRecord record);
    List<AuditRecord> findByOperation(@Param("operation") String operation);
    List<AuditRecord> findBySource(@Param("source") String source);
}
