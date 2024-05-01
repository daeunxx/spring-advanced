package org.example.springadvanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.example.springadvanced.trace.TraceStatus;
import org.example.springadvanced.trace.tracer.TracerV1;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

  private final OrderRepositoryV1 orderRepository;
  private final TracerV1 tracer;

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
