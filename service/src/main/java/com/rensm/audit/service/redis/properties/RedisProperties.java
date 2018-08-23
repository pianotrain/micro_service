package com.rensm.audit.service.redis.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(
        prefix = "spring.redis"
)
public class RedisProperties {
    private int dataBase = 0;
    private String host;
    private int port = 6379;
    private String password;
    private int timeout;
    private final Pool pool = new Pool();
    private final Cluster cluster = new Cluster();

    public RedisProperties() {
    }

    public int getDataBase() {
        return this.dataBase;
    }

    public void setDataBase(int dataBase) {
        this.dataBase = dataBase;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Pool getPool() {
        return this.pool;
    }

    public Cluster getCluster() {
        return this.cluster;
    }

    public static class Cluster {
        private List<String> nodes = new ArrayList();

        public Cluster() {
        }

        public List<String> getNodes() {
            return this.nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }
    }

    public static class Pool {
        private int maxIdle = 8;
        private int minIdle = 0;
        private int maxActive = 20;
        private int maxWait = 30000;

        public Pool() {
        }

        public int getMaxIdle() {
            return this.maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return this.minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxActive() {
            return this.maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public int getMaxWait() {
            return this.maxWait;
        }

        public void setMaxWait(int maxWait) {
            this.maxWait = maxWait;
        }
    }
}

