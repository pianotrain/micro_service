package com.rensm.audit.service.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisClient implements IRedisClient {
    private RedisTemplate<String, Object> redis;

    public RedisClient(RedisTemplate<String, Object> redis) {
        this.redis = redis;
    }

    public Object get(String key) {
        return this.redis.opsForValue().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        return (T) this.get(key);
    }

    public void set(String key, Object value) {
        this.redis.opsForValue().set(key, value);
    }

    public void set(String key, Object value, int time, TimeUnit unit) {
        this.redis.opsForValue().set(key, value);
        this.redis.expire(key, (long)time, unit);
    }

    public void sadd(String key, Object value) {
        this.redis.opsForSet().add(key, new Object[]{value});
    }

    public <T> T spop(String key, Class<T> clazz) {
        return (T) this.redis.opsForSet().pop(key);
    }

    public boolean setNX(String key, String value) {
        return this.redis.opsForValue().setIfAbsent(key, value).booleanValue();
    }

    public boolean setNX(String key, String value, int time, TimeUnit unit) {
        boolean result = this.redis.opsForValue().setIfAbsent(key, value).booleanValue();
        if(result) {
            this.redis.expire(key, (long)time, unit);
        }

        return result;
    }

    public void delete(String key) {
        this.redis.delete(key);
    }

    public Set<String> keys(String pattern) {
        return this.redis.keys(pattern);
    }

    public Long incr(String key, long value) {
        return this.redis.opsForValue().increment(key, value);
    }

    public void del(Collection<String> keys) {
        this.redis.delete(keys);
    }
}

