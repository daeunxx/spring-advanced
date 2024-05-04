package org.example.logtrace.app.v2;

import lombok.RequiredArgsConstructor;
import org.example.logtrace.trace.TraceId;
import org.example.logtrace.trace.TraceStatus;
import org.example.logtrace.trace.mocktrace.MockTraceV2;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

  private final MockTraceV2 tracer;

  public void save(TraceId traceId, String itemId) {

    TraceStatus traceStatus = null;
    try {
      traceStatus = tracer.beginSync(traceId, "OrderRepository.save()");

      //저장 로직
      if (itemId.equals("ex")) {
        throw new IllegalStateException("예외 발생");
      }
      sleep(1000);

      tracer.end(traceStatus);
    } catch (Exception e) {
      tracer.exception(traceStatus, e);
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
