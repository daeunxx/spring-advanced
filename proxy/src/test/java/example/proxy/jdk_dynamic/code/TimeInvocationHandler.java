package example.proxy.jdk_dynamic.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TimeInvocationHandler implements InvocationHandler {

  private final Object target;

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    log.info("TimeProxy 실행");
    long startTime = System.currentTimeMillis();

    Object result = method.invoke(target, args);

    log.info("TimeProxy 종료 resultTime={}", System.currentTimeMillis() - startTime);
    return result;
  }

}
