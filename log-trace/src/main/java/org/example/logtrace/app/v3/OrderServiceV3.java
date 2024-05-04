package org.example.logtrace.app.v3;

import lombok.RequiredArgsConstructor;
import org.example.logtrace.trace.TraceStatus;
import org.example.logtrace.trace.logtrace.LogTrace;
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
