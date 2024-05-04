package org.example.logtrace.app.v1;

import lombok.RequiredArgsConstructor;
import org.example.logtrace.trace.TraceStatus;
import org.example.logtrace.trace.mocktrace.MockTraceV1;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

  private final OrderRepositoryV1 orderRepository;
  private final MockTraceV1 tracer;

  public void orderItem(String itemId) {

    TraceStatus traceStatus = null;
    try {
      traceStatus = tracer.begin("OrderService.orderItem()");

      //저장 로직
      orderRepository.save(itemId);

      tracer.end(traceStatus);
    } catch (Exception e) {
      tracer.exception(traceStatus, e);
      throw e;
    }
  }
}
