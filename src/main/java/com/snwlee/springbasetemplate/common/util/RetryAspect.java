package com.snwlee.springbasetemplate.common.util;

import com.snwlee.springbasetemplate.common.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j(topic = "RetryAspect")
@Aspect
@Component
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint proceedingJoinPoint, Retry retry) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        log.info("[retry] Entering method={} retry={}", methodName, retry.value());

        int maxRetry = retry.value();
        Exception lastException = null;

        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            try {
                log.info("[retry] try count={}/{}", retryCount, maxRetry);
                return proceedingJoinPoint.proceed();
            } catch (Exception e) {
                lastException = e;
                log.warn("[retry] Retry failed: {}", e.getMessage());
            }
        }

        log.error("[retry] All retry attempts failed. Rethrowing the last exception: {}", Objects.requireNonNull(lastException).getMessage());
        throw Objects.requireNonNull(lastException);
    }
}


