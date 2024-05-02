package org.example.springadvanced.app.v2;

import lombok.RequiredArgsConstructor;
import org.example.springadvanced.trace.TraceId;
import org.example.springadvanced.trace.TraceStatus;
import org.example.springadvanced.trace.tracer.TracerV2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

  private final OrderRepositoryV2 orderRepository;
  private final TracerV2 tracer;

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
