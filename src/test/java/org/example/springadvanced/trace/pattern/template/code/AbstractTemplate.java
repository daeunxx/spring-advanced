package org.example.springadvanced.trace.pattern.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

  public void execute() {
    long startTime = System.currentTimeMillis();

    //비즈니스 로직 실행
    call(); //상속
    //비즈니스 로직 종료

    long resultTime = System.currentTimeMillis() - startTime;
    log.info("resultTime={}", resultTime);
  }

  protected abstract void call();
}
