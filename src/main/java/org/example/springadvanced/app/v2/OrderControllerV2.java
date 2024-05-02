package org.example.springadvanced.app.v2;

import lombok.RequiredArgsConstructor;
import org.example.springadvanced.trace.TraceStatus;
import org.example.springadvanced.trace.mocktrace.MockTraceV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

  private final OrderServiceV2 orderService;
  private final MockTraceV2 tracer;

  @GetMapping("/v2/request")
  public String request(String itemId) {

    TraceStatus traceStatus = null;
    try {
      traceStatus = tracer.begin("OrderController.request()");

      //요청 로직
      orderService.orderItem(traceStatus.getTraceId(), itemId);

      tracer.end(traceStatus);
      return "ok";
    } catch (Exception e) {
      tracer.exception(traceStatus, e);
      throw e;  //예외를 꼭 다시 던져야 함
    }
  }
}
