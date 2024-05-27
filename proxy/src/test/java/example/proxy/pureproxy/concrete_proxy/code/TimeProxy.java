package example.proxy.pureproxy.concrete_proxy.code;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class TimeProxy extends ConcreteLogic {

  private ConcreteLogic logic;

  @Override
  public String operation() {
    log.info("TimeDecorator 실행");
    long startTime = System.currentTimeMillis();

    String result = logic.operation();
    log.info("TimeDecorator 종료 resultTime={}ms", System.currentTimeMillis() - startTime);

    return result;
  }
}
