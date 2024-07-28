package org.example.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.aop.member.MemberService;
import org.example.aop.member.MemberServiceImpl;
import org.example.aop.member.annotation.ClassAop;
import org.example.aop.member.annotation.MethodAop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ParameterTest.ParameterAspect.class)
//@SpringBootTest
@SpringBootTest("spring.aop.proxy-target-class=false")
public class ParameterTest {

  @Autowired
  MemberService memberService;

  @Test
  void success() {
    log.info("memberService Proxy={}", memberService.getClass());
    memberService.hello("helloA");
  }

  @Slf4j
  @Aspect
  static class ParameterAspect {

    @Pointcut("execution(* org.example.aop.member..*(..))")
    private void allMember() {

    }

    @Around("allMember()")
    public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
      Object arg = joinPoint.getArgs()[0];
      log.info("[logArgs1]{}, arg={}", joinPoint.getSignature(), arg);
      return joinPoint.proceed();
    }

    @Around("allMember() && args(arg, ..)")
    public Object logArgs2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
      log.info("[logArgs2]{}, arg={}", joinPoint.getSignature(), arg);
      return joinPoint.proceed();
    }

    @Before("allMember() && args(arg, ..)")
    public void logArgs3(String arg) {
      log.info("[logArgs3] arg={}", arg);
    }

    //this는 프록시 호출
    @Before("allMember() && this(obj)")
    public void thisArgs1(JoinPoint joinPoint, MemberService obj) {
      log.info("[this1]{} obj={}", joinPoint.getSignature(), obj.getClass());
    }

    //jdk 동적 프록시가 생성되면 호출
    //CGLIB 프록시가 생성되면 호출X
    @Before("allMember() && this(obj)")
    public void thisArgs2(JoinPoint joinPoint, MemberServiceImpl obj) {
      log.info("[this2]{} obj={}", joinPoint.getSignature(), obj.getClass());
    }

    //target은 실제 객체 호출
    @Before("allMember() && target(obj)")
    public void targetArgs1(JoinPoint joinPoint, MemberService obj) {
      log.info("[target1]{}, obj={}", joinPoint.getSignature(), obj.getClass());
    }

    @Before("allMember() && target(obj)")
    public void targetArgs2(JoinPoint joinPoint, MemberServiceImpl obj) {
      log.info("[target2]{}, obj={}", joinPoint.getSignature(), obj.getClass());
    }

    @Before("allMember() && @target(annotation)")
    public void atTarget(JoinPoint joinPoint, ClassAop annotation) {
      log.info("[@target]{}, annotation={}", joinPoint.getSignature(), annotation);
    }

    @Before("allMember() && @within(annotation)")
    public void atWithin(JoinPoint joinPoint, ClassAop annotation) {
      log.info("[@within]{}, annotation={}", joinPoint.getSignature(), annotation);
    }

    @Before("allMember() && @annotation(annotation)")
    public void atAnnotation(JoinPoint joinPoint, MethodAop annotation) {
      log.info("[@annotation]{}, annotationValue={}", joinPoint.getSignature(), annotation.value());
    }
  }
}
