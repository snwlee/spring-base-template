package com.snwlee.springbasetemplate.common.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j(topic = "TraceAspect")
@Aspect
@Component
public class TraceAspect {

    @Before("@annotation(com.pgrrr.springbasetemplate.annotation.Trace)")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("[trace] Entering method={} args={}", methodName, args);
    }

    @AfterReturning(pointcut = "@annotation(com.pgrrr.springbasetemplate.annotation.Trace)", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("[trace] Exiting method={}, result={}", methodName, result);
    }
}

