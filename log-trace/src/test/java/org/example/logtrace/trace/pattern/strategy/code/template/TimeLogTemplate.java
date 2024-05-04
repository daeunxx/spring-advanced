package org.example.logtrace.trace.pattern.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

  public void execute(Callback callback) {
    long startTime = System.currentTimeMillis();

    //비즈니스 로직 실행
    callback.call(); //위임
    //비즈니스 로직 종료

    long resultTime = System.currentTimeMillis() - startTime;
    log.info("resultTime={}", resultTime);
  }
}
