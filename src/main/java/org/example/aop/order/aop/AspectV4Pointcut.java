package org.example.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV4Pointcut {

  @Around("org.example.aop.order.aop.Pointcuts.allOrder()")
  public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("[log] {}", joinPoint.getSignature()); //jointPoint 시그니처
    return joinPoint.proceed();
  }

  @Around("org.example.aop.order.aop.Pointcuts.orderAndService()")
  public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

    try {
      log.info("[Transaction start] {}", joinPoint.getSignature());
      Object result = joinPoint.proceed();
      log.info("[Transaction commit] {}", joinPoint.getSignature());
      return result;
    } catch (Exception e) {
      log.info("[Transaction rollback] {}", joinPoint.getSignature());
      throw e;
    } finally {
      log.info("[Resource release] {}", joinPoint.getSignature());
    }
  }
}
