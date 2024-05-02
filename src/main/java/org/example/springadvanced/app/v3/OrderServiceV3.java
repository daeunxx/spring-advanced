package org.example.springadvanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.example.springadvanced.trace.TraceId;
import org.example.springadvanced.trace.TraceStatus;
import org.example.springadvanced.trace.logtrace.LogTrace;
import org.example.springadvanced.trace.mocktrace.MockTraceV2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

  private final OrderRepositoryV3 orderRepository;
  private final LogTrace trace;

  public void orderItem(String itemId) {

    TraceStatus traceStatus = null;
    try {
      traceStatus = trace.begin("OrderService.orderItem()");

      //저장 로직
      orderRepository.save(itemId);

      trace.end(traceStatus);
    } catch (Exception e) {
      trace.exception(traceStatus, e);
      throw e;
    }
  }
}
