package org.example.logtrace.app.v4;

import lombok.RequiredArgsConstructor;
import org.example.logtrace.trace.logtrace.LogTrace;
import org.example.logtrace.trace.template.AbstractTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

  private final OrderServiceV4 orderService;
  private final LogTrace trace;

  @GetMapping("/v4/request")
  public String request(String itemId) {

    AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
      @Override
      protected String call() {
        orderService.orderItem(itemId);
        return "ok";
      }
    };

    return template.execute("OrderController.request()");
  }
}
