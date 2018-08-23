package com.rensm.audit.service;

import com.rensm.audit.service.apollo.ApolloConfigBase;
import org.springframework.beans.factory.annotation.Value;

public class TestConfig extends ApolloConfigBase {

    @Value("${testKey:test}")//如果配置中心没有值，默认key为test的value值为test
    private String tk;

    @Value("${redis.cache.enabled:true)")
    private String redisEnable;

    public String getTk() {
        return tk; //config.getProperty("testKey", "test");
    }

    public boolean isRedisEnable() {
        return Boolean.valueOf(redisEnable);
    }
}
