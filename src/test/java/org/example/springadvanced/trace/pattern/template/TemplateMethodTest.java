package org.example.springadvanced.trace.pattern.template;

import lombok.extern.slf4j.Slf4j;
import org.example.springadvanced.trace.pattern.template.code.AbstractTemplate;
import org.example.springadvanced.trace.pattern.template.code.SubClassLogic1;
import org.example.springadvanced.trace.pattern.template.code.SubClassLogic2;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

  @Test
  void templateMethodV0() {
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
  void templateMethodV1() {
    AbstractTemplate template1 = new SubClassLogic1();
    template1.execute();

    AbstractTemplate template2 = new SubClassLogic2();
    template2.execute();
  }

  @Test
  void templateMethodV2() {
    AbstractTemplate template1 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("비즈니스 로직1 실행");
      }
    };
    log.info("클래스 이름1={}", template1.getClass());
    template1.execute();

    AbstractTemplate template2 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("비즈니스 로직2 실행");
      }
    };
    log.info("클래스 이름2={}", template2.getClass());
    template2.execute();
  }
}
