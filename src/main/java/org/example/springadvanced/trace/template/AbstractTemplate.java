package org.example.springadvanced.trace.template;

import lombok.RequiredArgsConstructor;
import org.example.springadvanced.trace.TraceStatus;
import org.example.springadvanced.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {

  private final LogTrace trace;

  public T execute(String message) {
    TraceStatus traceStatus = null;
    try {
      traceStatus = trace.begin(message);

      //로직 호출
      T result = call();

      trace.end(traceStatus);
      return result;
    } catch (Exception e) {
      trace.exception(traceStatus, e);
      throw e;
    }
  }

  protected abstract T call();
}
