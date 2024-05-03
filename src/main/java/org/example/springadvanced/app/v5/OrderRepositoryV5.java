package org.example.springadvanced.app.v5;

import lombok.RequiredArgsConstructor;
import org.example.springadvanced.trace.callback.TraceTemplate;
import org.example.springadvanced.trace.logtrace.LogTrace;
import org.example.springadvanced.trace.template.AbstractTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

  private final TraceTemplate template;

  public OrderRepositoryV5(LogTrace trace) {
    this.template = new TraceTemplate(trace);
  }

  public void save(String itemId) {
    template.execute("OrderRepository.save()", () -> {
      if (itemId.equals("ex")) {
        throw new IllegalStateException("예외 발생");
      }
      sleep(1000);
      return null;
    });
  }

  public void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
