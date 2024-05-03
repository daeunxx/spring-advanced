package org.example.logtrace.app.v3;

import lombok.RequiredArgsConstructor;
import org.example.logtrace.trace.TraceStatus;
import org.example.logtrace.trace.logtrace.LogTrace;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

  private final LogTrace trace;

  public void save(String itemId) {

    TraceStatus traceStatus = null;
    try {
      traceStatus = trace.begin("OrderRepository.save()");

      //저장 로직
      if (itemId.equals("ex")) {
        throw new IllegalStateException("예외 발생");
      }
      sleep(1000);

      trace.end(traceStatus);
    } catch (Exception e) {
      trace.exception(traceStatus, e);
      throw e;
    }
  }

  public void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
