package example.proxy.config.v2_dynamic_proxy.handler;

import example.proxy.trace.TraceStatus;
import example.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.util.PatternMatchUtils;

@RequiredArgsConstructor
public class LogTraceFilterHandler implements InvocationHandler {

  private final Object target;
  private final LogTrace trace;
  private final String[] patterns;

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    //메서드 이름 필터
    String methodName = method.getName();

    if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
      return method.invoke(target, args);
    }

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
