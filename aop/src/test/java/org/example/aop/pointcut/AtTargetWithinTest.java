package org.example.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.aop.member.annotation.ClassAop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(AtTargetWithinTest.Config.class)
@SpringBootTest
public class AtTargetWithinTest {

  @Autowired
  Child child;

  @Test
  void success() {
    log.info("child Proxy={}", child.getClass());
    child.childMethod();
    child.parentMethod();
  }

  @Slf4j
  @Aspect
  static class AtTargetWithinAspect {

    //@target: 인스턴스 기준으로 모든 메서드의 조인 포인트 선정, 부모 타입 메서드도 적용
    @Around("execution(* org.example.aop..*(..)) && @target(org.example.aop.member.annotation.ClassAop)")
    public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[@target] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }

    //@within: 선택된 클래스 내부에 있는 메서드만 조인 포인트 선정, 부모 타입 메서드 적용X
    @Around("execution(* org.example.aop..*(..)) && @within(org.example.aop.member.annotation.ClassAop)")
    public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("[@within] {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }
  }

  static class Parent {

    public void parentMethod() {
    }
  }

  @ClassAop
  static class Child extends Parent {

    public void childMethod() {
    }
  }

  static class Config {

    @Bean
    public Parent parent() {
      return new Parent();
    }

    @Bean
    public Child child() {
      return new Child();
    }

    @Bean
    public AtTargetWithinAspect atTargetWithinAspect() {
      return new AtTargetWithinAspect();
    }
  }

}

