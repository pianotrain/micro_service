package com.rensm.audit.service.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by DELL on 2018/7/23.
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    private ObjectMapper objectMapper;

    public LogAspect() {
        objectMapper = new ObjectMapper();
    }

    @Pointcut(value = "@annotation(com.rensm.audit.service.aspect.annotation.LogAnnotation)")
    private void log(){

    }

    @Around(value = "log()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        Class<? extends Object> clazz = point.getTarget().getClass();
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        // TODO: 该注解只能用于方法
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method method = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        LOGGER.debug("执行类:" + clazz);
        LOGGER.debug("方法:" + method);
        StringBuilder inputPara = new StringBuilder();
        int count = 0;
        for (Object item : point.getArgs()) {
            if (count++ == 0) {
                inputPara.append(objectMapper.writeValueAsString(item));
            } else {
                inputPara.append(";\t").append(objectMapper.writeValueAsString(item));
            }
        }
        LOGGER.debug("入参：" + inputPara.toString());
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        long endTime = System.currentTimeMillis();
        String s = objectMapper.writeValueAsString(result);
        LOGGER.debug("出参: " + s );
        LOGGER.debug("运行消耗: " + (endTime - startTime));
        return result;
    }

    @AfterThrowing(value = "log()", throwing = "ex")
    public void afterThrowing(JoinPoint point, Exception ex){
        LOGGER.error("异常结果: " + ex);
    }
}
