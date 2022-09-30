package com.bonwonder.crmdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.bonwonder.crmdemo.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.bonwonder.crmdemo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("forServicePackage() || forControllerPackage()")
    private void forAllFlow() {
    }

    @Before("forAllFlow()")
    public void before(JoinPoint joinPoint) {
        logger.info(">>>>>>>>>> Before calling" +
                joinPoint.getSignature().toShortString());

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info(">>>>>>>>>> With argument: " + arg);
        }
    }

    @AfterReturning(pointcut = "forAllFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("<<<<<<<<<< Object returned: from " + joinPoint.getSignature().toShortString());

        logger.info("<<<<<<<<<< Result object: " + result);

    }
}
