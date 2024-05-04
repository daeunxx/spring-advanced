package org.example.logtrace.app.v4;

import lombok.RequiredArgsConstructor;
import org.example.logtrace.trace.logtrace.LogTrace;
import org.example.logtrace.trace.template.AbstractTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

  private final LogTrace trace;

  public void save(String itemId) {

    AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
      @Override
      protected Void call() {
        if (itemId.equals("ex")) {
          throw new IllegalStateException("예외 발생");
        }
        sleep(1000);

        return null;
      }
    };
    template.execute("OrderRepository.save()");
  }

  public void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
