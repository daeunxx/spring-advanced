package org.example.logtrace.app.v4;

import lombok.RequiredArgsConstructor;
import org.example.logtrace.trace.logtrace.LogTrace;
import org.example.logtrace.trace.template.AbstractTemplate;
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
