package org.example.aop.member;

import org.example.aop.member.annotation.ClassAop;
import org.example.aop.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService{

  @Override
  @MethodAop("test value")
  public String hello(String param) {
    return "ok";
  }

  public String internal(String param) {
    return "ok";
  }
}