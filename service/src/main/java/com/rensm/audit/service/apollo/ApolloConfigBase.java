package com.rensm.audit.service.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Set;

public abstract class ApolloConfigBase {
    private static final Logger logger = LoggerFactory.getLogger(ApolloConfigBase.class);
    @ApolloConfig("application")
    protected Config config;

    public ApolloConfigBase() {
    }

    @ApolloConfigChangeListener({"application"})
    protected void anotherOnChange(ConfigChangeEvent changeEvent) {
        Set<String> changedKeys = changeEvent.changedKeys();
        Iterator var3 = changedKeys.iterator();

        while(var3.hasNext()) {
            String key = (String)var3.next();
            ConfigChange change = changeEvent.getChange(key);
            logger.info(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", new Object[]{change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()}));
        }

    }
}

