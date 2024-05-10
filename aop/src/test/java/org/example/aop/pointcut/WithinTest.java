package org.example.aop.pointcut;

import java.lang.reflect.Method;
import org.assertj.core.api.Assertions;
import org.example.aop.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public class WithinTest {

  AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
  Method helloMethod;

  @BeforeEach
  public void init() throws NoSuchMethodException {
    helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
  }

  @Test
  void withinExtract() {
    pointcut.setExpression("within(org.example.aop.member.MemberServiceImpl)");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void withinStar() {
    pointcut.setExpression("within(org.example.aop.member.*Service*)");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void withinSubPackage() {
    pointcut.setExpression("within(org.example.aop..*)");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  @DisplayName("within은 타겟의 타입에만 직접 적용, 인터페이스나 부모 클래스 선정 불가")
  void withinSuperTypeFalse() {
    pointcut.setExpression("within(org.example.aop.member.MemberService)");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
  }

  @Test
  @DisplayName("execution은 타입 기반, 인터페이스나 부모 클래스 선정 가능")
  void executionSuperTypeTrue() {
    pointcut.setExpression("execution(* org.example.aop.member.MemberService.*(..))");
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

}
