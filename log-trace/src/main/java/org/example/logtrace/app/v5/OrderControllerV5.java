package org.example.logtrace.app.v5;

import org.example.logtrace.trace.callback.TraceTemplate;
import org.example.logtrace.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

  private final OrderServiceV5 orderService;
  private final TraceTemplate template;

  public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
    this.orderService = orderService;
    this.template = new TraceTemplate(trace);
  }

  @GetMapping("/v5/request")
  public String request(String itemId) {
    return template.execute("OrderController.request()", () -> {
      orderService.orderItem(itemId);
      return "ok";
    });
  }
}
