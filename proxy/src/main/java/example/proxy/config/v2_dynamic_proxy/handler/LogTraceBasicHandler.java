package example.proxy.config.v2_dynamic_proxy.handler;

import example.proxy.trace.TraceStatus;
import example.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogTraceBasicHandler implements InvocationHandler {

  private final Object target;
  private final LogTrace trace;

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    TraceStatus status = null;
    try {
      String message = method.getDeclaringClass() + "." + method.getName() + "()";
      status = trace.begin(message);

      //로직 호출
      Object result = method.invoke(target, args);

      trace.end(status);
      return result;
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}
