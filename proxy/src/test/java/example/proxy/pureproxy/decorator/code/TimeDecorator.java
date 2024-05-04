package example.proxy.pureproxy.decorator.code;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class TimeDecorator implements Component{

  private Component component;

  @Override
  public String operation() {
    log.info("TimeDecorator 실행");
    long startTime = System.currentTimeMillis();

    String result = component.operation();
    log.info("TimeDecorator 종료 resultTime={}ms", System.currentTimeMillis() - startTime);

    return result;
  }
}
