package org.example.aop.pointcut;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Method;
import org.example.aop.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public class ArgsTest {

  Method helloMethod;
  AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

  @BeforeEach
  public void init() throws NoSuchMethodException {
    helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
  }

  private AspectJExpressionPointcut pointcut(String expression) {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression(expression);
    return pointcut;
  }

  @Test
  void argsBasic() {
    pointcut.setExpression("args(String)");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  void args() {
    assertThat(pointcut("args(String)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(Object)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args()").matches(helloMethod, MemberServiceImpl.class)).isFalse();
    assertThat(pointcut("args(..)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(*)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(String, ..)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  /**
   * execution(* *(java.io.Serializable)): 메서드의 시그니처로 판단(정적)
   * args(java.io.Serializable): 런타임에 전달된 인수로 판단(동적)
   */
  @Test
  void argsVsExecution() {
    //args
    assertThat(pointcut("args(String)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(java.io.Serializable)").matches(helloMethod,
        MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("args(Object)").matches(helloMethod, MemberServiceImpl.class)).isTrue();

    //execution
    assertThat(
        pointcut("execution(* *(String))").matches(helloMethod, MemberServiceImpl.class)).isTrue();
    assertThat(pointcut("execution(* *(java.io.Serializable))").matches(helloMethod,
        MemberServiceImpl.class)).isFalse();
    assertThat(
        pointcut("execution(* *(Object))").matches(helloMethod, MemberServiceImpl.class)).isFalse();
  }

}
