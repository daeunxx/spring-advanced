package org.example.logtrace.app.v2;

import lombok.RequiredArgsConstructor;
import org.example.logtrace.trace.TraceId;
import org.example.logtrace.trace.TraceStatus;
import org.example.logtrace.trace.mocktrace.MockTraceV2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

  private final OrderRepositoryV2 orderRepository;
  private final MockTraceV2 tracer;

  public void orderItem(TraceId traceId, String itemId) {

    TraceStatus traceStatus = null;
    try {
      traceStatus = tracer.beginSync(traceId, "OrderService.orderItem()");

      //저장 로직
      orderRepository.save(traceStatus.getTraceId(), itemId);

      tracer.end(traceStatus);
    } catch (Exception e) {
      tracer.exception(traceStatus, e);
      throw e;
    }
  }
}
