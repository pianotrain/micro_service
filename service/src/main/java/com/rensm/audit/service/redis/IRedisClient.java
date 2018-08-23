package com.rensm.audit.service.redis;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by DELL on 2018/8/14.
 */
public interface IRedisClient {
    Object get(String var1);

    <T> T get(String var1, Class<T> var2);

    void set(String var1, Object var2);

    void set(String var1, Object var2, int var3, TimeUnit var4);

    void sadd(String var1, Object var2);

    <T> T spop(String var1, Class<T> var2);

    boolean setNX(String var1, String var2);

    boolean setNX(String var1, String var2, int var3, TimeUnit var4);

    void delete(String var1);

    Set<String> keys(String var1);

    Long incr(String var1, long var2);

    void del(Collection<String> var1);
}
