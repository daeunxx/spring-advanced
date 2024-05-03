package org.example.springadvanced.trace.pattern.strategy.code.strategy;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 파라미터로 전략을 보관하는 방식
 */
@Slf4j
@AllArgsConstructor
public class ContextV2 {

  public void execute(Strategy strategy) {
    long startTime = System.currentTimeMillis();

    //비즈니스 로직 실행
    strategy.call(); //위임
    //비즈니스 로직 종료

    long resultTime = System.currentTimeMillis() - startTime;
    log.info("resultTime={}", resultTime);
  }
}
