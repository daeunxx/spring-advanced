package org.example.logtrace.trace.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.logtrace.trace.TraceStatus;
import org.example.logtrace.trace.logtrace.LogTrace;

@Slf4j
@RequiredArgsConstructor
public class TraceTemplate {

  private final LogTrace trace;

  public <T> T execute(String message, TraceCallback<T> callback) {
    TraceStatus traceStatus = null;
    try {
      traceStatus = trace.begin(message);

      //로직 호출
      T result = callback.call();

      trace.end(traceStatus);
      return result;
    } catch (Exception e) {
      trace.exception(traceStatus, e);
      throw e;
    }
  }
}
