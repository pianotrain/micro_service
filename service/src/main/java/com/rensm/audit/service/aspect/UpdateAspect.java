package com.rensm.audit.service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by DELL on 2018/8/10.
 */
@Aspect
@Component
public class UpdateAspect {

    @Pointcut(value = "@annotation(com.rensm.audit.service.aspect.annotation.RemoveLastModifyTime)")
    private void remove(){

    }

    @Before(value = "remove()")
    public void before(JoinPoint point) {
        Object[] args = point.getArgs();
        for (Object item : args){
            Class<?> clazz = item.getClass();
            try {
                Field lastModifyTime = clazz.getDeclaredField("lastModifyTime");
                if (lastModifyTime != null){
                    Class<?> fieldType = lastModifyTime.getType();
                    Method setLastModifyTime = clazz.getDeclaredMethod("setLastModifyTime", fieldType);
                    if (setLastModifyTime != null){
                        setLastModifyTime.invoke(item, (Object) null);
                    }
                }
            }catch (NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
