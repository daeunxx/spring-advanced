package org.example.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 수정자를 통해 주입
 * 생성자 주입 시, 순환 사이클이 만들어져 오류 발생
 */
@Slf4j
@Component
public class CallServiceV1 {

  private CallServiceV1 callServiceV1;

  @Autowired
  public void setCallServiceV1(CallServiceV1 callServiceV1) {
    log.info("callServiceV1 setter={}", callServiceV1.getClass());
    this.callServiceV1 = callServiceV1;
  }

  public void external() {
    log.info("call external");
    callServiceV1.internal(); //외부 메서드 호출
  }

  public void internal() {
    log.info("call internal");
  }
}
