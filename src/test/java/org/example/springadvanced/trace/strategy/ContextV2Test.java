package org.example.springadvanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.example.springadvanced.trace.strategy.code.ContextV2;
import org.example.springadvanced.trace.strategy.code.Strategy;
import org.example.springadvanced.trace.strategy.code.StrategyLogic1;
import org.example.springadvanced.trace.strategy.code.StrategyLogic2;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

  /**
   * 전략 패턴 적용
   */
  @Test
  void strategyV1() {
    ContextV2 context = new ContextV2();
    context.execute(new StrategyLogic1());
    context.execute(new StrategyLogic2());
  }

  /**
   * 전략 패턴 익명 내부 클래스
   */
  @Test
  void strategyV2() {
    ContextV2 context = new ContextV2();
    context.execute(new Strategy() {
      @Override
      public void call() {
        log.info("비즈니스 로직1 실행");
      }
    });
    context.execute(new Strategy() {
      @Override
      public void call() {
        log.info("비즈니스 로직2 실행");
      }
    });
  }

  /**
   * 전략 패턴 익명 내부 클래스 -> 람다 사용
   */
  @Test
  void strategyV3() {
    ContextV2 context = new ContextV2();
    context.execute(() -> log.info("비즈니스 로직1 실행"));
    context.execute(() -> log.info("비즈니스 로직2 실행"));
  }
}
