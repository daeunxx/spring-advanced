package org.example.springadvanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.example.springadvanced.trace.TraceStatus;
import org.example.springadvanced.trace.tracer.TracerV1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

  private final OrderServiceV1 orderService;
  private final TracerV1 tracer;

  @GetMapping("/v1/request")
  public String request(String itemId) {

    TraceStatus traceStatus = null;
    try {
      traceStatus = tracer.begin("OrderController.request()");

      //요청 로직
      orderService.orderItem(itemId);

      tracer.end(traceStatus);
      return "ok";
    } catch (Exception e) {
      tracer.exception(traceStatus, e);
      throw e;  //예외를 꼭 다시 던져야 함
    }
  }
}
