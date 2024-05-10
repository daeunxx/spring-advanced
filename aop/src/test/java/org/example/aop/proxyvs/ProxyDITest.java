package org.example.aop.proxyvs;

import lombok.extern.slf4j.Slf4j;
import org.example.aop.member.MemberService;
import org.example.aop.member.MemberServiceImpl;
import org.example.aop.proxyvs.code.ProxyDIAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ProxyDIAspect.class)
@SpringBootTest(properties = "spring.aop.proxy-target-class=false") //JDK 동적 프록시
//@SpringBootTest(properties = "spring.aop.proxy-target-class=true") //CGLIB 프록시
public class ProxyDITest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberServiceImpl memberServiceImpl;

  @Test
  void go() {
    log.info("memberService class={}", memberService.getClass());
    log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
    memberServiceImpl.hello("hello");
  }
}
