package org.example.aop.proxyvs;

import lombok.extern.slf4j.Slf4j;
import org.example.aop.member.MemberService;
import org.example.aop.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

  @Test
  void jdkProxy() {
    MemberServiceImpl target = new MemberServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(false); //JDK 동적 프록시(생략 가능: default JDK 동적 프록시)

    MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

    //JDK 동적 프록시는 구현 클래스로 캐스팅 불가(ClassCastException)
    Assertions.assertThrows(ClassCastException.class, () -> {
          MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        }
    );
  }

  @Test
  void cglibProxy() {
    MemberServiceImpl target = new MemberServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(true); //CGLIB 프록시

    //CGLIB 프록시 인터페이스로 캐스팅 가능
    MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();
    log.info("proxy class={}", memberServiceProxy.getClass());

    //인터페이스로 캐스팅된 CGLIB 프록시 구현 클래스로 캐스팅 가능
    MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
  }
}
