package org.example.springadvanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.example.springadvanced.trace.strategy.code.ContextV1;
import org.example.springadvanced.trace.strategy.code.StrategyLogic1;
import org.example.springadvanced.trace.strategy.code.StrategyLogic2;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

  @Test
  void strategyV0() {
    logic1();
    logic2();
  }

  private void logic1() {
    long startTime = System.currentTimeMillis();

    //비즈니스 로직 실행
    log.info("비즈니스 로직1 실행");
    //비즈니스 로직 종료

    long resultTime = System.currentTimeMillis() - startTime;
    log.info("resultTime={}", resultTime);
  }

  private void logic2() {
    long startTime = System.currentTimeMillis();

    //비즈니스 로직 실행
    log.info("비즈니스 로직2 실행");
    //비즈니스 로직 종료

    long resultTime = System.currentTimeMillis() - startTime;
    log.info("resultTime={}", resultTime);
  }

  @Test
  void strategyV1() {
    StrategyLogic1 logic1 = new StrategyLogic1();
    ContextV1 context1 = new ContextV1(logic1);
    context1.execute();

    StrategyLogic2 logic2 = new StrategyLogic2();
    ContextV1 context2 = new ContextV1(logic2);
    context2.execute();
  }


}
