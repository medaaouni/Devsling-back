package com.assignement.car_sales_garage.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.assignement.car_sales_garage.controllers.CarController.*(..))")
    public void carControllerMethods(){
        // Pointcut method - no implementation needed
    }

    @Around("carControllerMethods()")
    public Object logCarControllerCalls(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();

        log.info("START: {}.{} called with args: {}", className, methodName, Arrays.toString(args));

        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startTime;
        log.info("END: {}.{} returned: {} (Execution time: {} ms)", className, methodName, result, executionTime);

        return result;
    }
}
