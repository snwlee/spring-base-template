package com.snwlee.springbasetemplate.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LogAspect {

    ThreadLocal<StopWatch> stopWatch = new ThreadLocal<>();

    @Pointcut("execution(* com.snwlee.springbasetemplate..*Controller.*(..))")
    public void controller() {}

    @Before("controller()")
    public void before(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        log.info("======= method name = {} =======", method.getName());

        Object[] args = joinPoint.getArgs();
        if (args.length <= 0) log.info("no parameter");
        for (Object arg : args) {
            if (arg != null) {
                log.info("parameter type = {}", arg.getClass().getSimpleName());
                log.info("parameter value = {}", arg);
            }
        }
        StopWatch stopWatch = new StopWatch();
        this.stopWatch.set(stopWatch);
        stopWatch.start();
    }

    @AfterReturning(value = "controller()", returning = "ret")
    public void afterReturning(JoinPoint joinPoint, Object ret) {
        Method method = getMethod(joinPoint);
        log.info("======= method name = {} =======", method.getName());

        log.info("return type = {}", ret.getClass().getSimpleName());
        log.info("return value = {}", ret);

        StopWatch stopWatch = this.stopWatch.get();
        stopWatch.stop();
        log.info("======= method name = {} elapsed time = {} =======", method.getName(), stopWatch.getTotalTimeMillis() + "ms");
        this.stopWatch.remove();
    }

    @AfterThrowing(value = "controller()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        Method method = getMethod(joinPoint);
        log.error("======= method name = {} =======", method.getName());

        log.error("exception = {}", ex.getClass().getSimpleName());
        log.error("exception message = {}", ex.getMessage());

        StopWatch stopWatch = this.stopWatch.get();
        stopWatch.stop();
        log.info("======= method name = {} elapsed time = {} =======", method.getName(), stopWatch.getTotalTimeMillis() + "ms");
        this.stopWatch.remove();
    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}