package com.rensm.audit.service.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.rensm.audit.service.redis.IRedisClient;
import com.rensm.audit.service.redis.RedisClient;
import com.rensm.audit.service.redis.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableConfigurationProperties({RedisProperties.class})
@EnableCaching
public class RedisAutoConfiguration {
    @Autowired
    private RedisProperties redisProperties;

    public RedisAutoConfiguration() {
    }

    @Bean(
            name = {"myRedisConnectionFactory"}
    )
    JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(this.redisProperties.getPool().getMaxIdle());
        poolConfig.setMinIdle(this.redisProperties.getPool().getMinIdle());
        poolConfig.setMaxWaitMillis((long)this.redisProperties.getPool().getMaxWait());
        poolConfig.setMaxTotal(this.redisProperties.getPool().getMaxActive());
        JedisConnectionFactory factory;
        if(this.redisProperties.getCluster().getNodes().size() > 0) {
            RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(this.redisProperties.getCluster().getNodes());
            factory = new JedisConnectionFactory(clusterConfiguration, poolConfig);
            factory.setPassword(this.redisProperties.getPassword());
            factory.setTimeout(this.redisProperties.getTimeout());
            return factory;
        } else {
            factory = new JedisConnectionFactory();
            factory.setDatabase(this.redisProperties.getDataBase());
            factory.setHostName(this.redisProperties.getHost());
            factory.setPort(this.redisProperties.getPort());
            factory.setPassword(this.redisProperties.getPassword());
            factory.setTimeout(this.redisProperties.getTimeout());
            factory.setPoolConfig(poolConfig);
            return factory;
        }
    }

    @Bean(
            name = {"myRedisTemplate"}
    )
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("myRedisConnectionFactory") RedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
        om.enableDefaultTyping(DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public IRedisClient redisClient(@Qualifier("myRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
        return new RedisClient(redisTemplate);
    }
}
