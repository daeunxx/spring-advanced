package example.proxy.config.v1_proxy.interface_proxy;

import example.proxy.app.v1.OrderRepositoryV1;
import example.proxy.trace.TraceStatus;
import example.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

  private final OrderRepositoryV1 target;
  private final LogTrace trace;

  @Override
  public void save(String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderRepository.save()");
      //target 호출
      target.save(itemId);
      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}
