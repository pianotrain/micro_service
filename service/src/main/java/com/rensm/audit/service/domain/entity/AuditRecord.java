package com.rensm.audit.service.domain.entity;

import com.alibaba.fastjson.JSON;
import com.rensm.api.model.RecordItem;

import java.util.Date;
import java.util.Map;

public class AuditRecord implements IEntity<RecordItem> {

    private String operation;
    private String source;
    private Date occurTime;
    private Map<String, Object> parameters;
    private String keys;

    public AuditRecord(){}

    public AuditRecord(RecordItem item){

        operation = item.getOperation();
        source = item.getSource();
        occurTime = item.getOccurTime();

        keys = parseKeys(item.getParameters());
    }

    public RecordItem toDto() {

        RecordItem item = new RecordItem();
        item.setOperation(operation);
        item.setSource(source);
        item.setOccurTime(occurTime);

        assembleParameters(item.getParameters());

        return item;
    }

    private void assembleParameters(Map<String, Object> parameters) {

        Map pairs = (Map)JSON.parse(keys);
        for (Object pair : pairs.entrySet()){
            parameters.put(((Map.Entry)pair).getKey().toString(), ((Map.Entry)pair).getValue());
        }
    }

    private String parseKeys(Map<String, Object> parameters) {

        return JSON.toJSONString(parameters);
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }
}
