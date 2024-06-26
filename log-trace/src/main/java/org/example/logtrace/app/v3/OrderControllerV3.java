package org.example.logtrace.app.v3;

import lombok.RequiredArgsConstructor;
import org.example.logtrace.trace.TraceStatus;
import org.example.logtrace.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

  private final OrderServiceV3 orderService;
  private final LogTrace trace;

  @GetMapping("/v3/request")
  public String request(String itemId) {

    TraceStatus traceStatus = null;
    try {
      traceStatus = trace.begin("OrderController.request()");

      //요청 로직
      orderService.orderItem(itemId);

      trace.end(traceStatus);
      return "ok";
    } catch (Exception e) {
      trace.exception(traceStatus, e);
      throw e;  //예외를 꼭 다시 던져야 함
    }
  }
}
