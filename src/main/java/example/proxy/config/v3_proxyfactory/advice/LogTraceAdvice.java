package example.proxy.config.v3_proxyfactory.advice;

import example.proxy.trace.TraceStatus;
import example.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@RequiredArgsConstructor
public class LogTraceAdvice implements MethodInterceptor {

  private final LogTrace trace;

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    TraceStatus status = null;
    try {
      Method method = invocation.getMethod();
      String message = method.getDeclaringClass() + "." + method.getName() + "()";
      status = trace.begin(message);

      //로직 호출
      Object result = invocation.proceed();

      trace.end(status);
      return result;
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}
