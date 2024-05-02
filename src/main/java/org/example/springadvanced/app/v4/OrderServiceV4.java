package org.example.springadvanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.example.springadvanced.trace.TraceStatus;
import org.example.springadvanced.trace.logtrace.LogTrace;
import org.example.springadvanced.trace.template.AbstractTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

  private final OrderRepositoryV4 orderRepository;
  private final LogTrace trace;

  public void orderItem(String itemId) {

    AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
      @Override
      protected Void call() {
        orderRepository.save(itemId);
        return null;
      }
    };
    template.execute("OrderService.orderItem()");
  }
}
