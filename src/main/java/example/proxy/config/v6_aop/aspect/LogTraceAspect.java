package example.proxy.config.v6_aop.aspect;

import example.proxy.trace.TraceStatus;
import example.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogTraceAspect {

  private final LogTrace trace;

  @Around("execution(* example.proxy.app..*(..))")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    TraceStatus status = null;
    try {
      String message = joinPoint.getSignature().toShortString();
      status = trace.begin(message);

      //로직 호출
      Object result = joinPoint.proceed();

      trace.end(status);
      return result;
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}
