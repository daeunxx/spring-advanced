package example.proxy.config.v1_proxy.concrete_proxy;

import example.proxy.app.v1.OrderControllerV1;
import example.proxy.app.v2.OrderControllerV2;
import example.proxy.app.v2.OrderServiceV2;
import example.proxy.trace.TraceStatus;
import example.proxy.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2 {

  private final OrderControllerV2 target;
  private final LogTrace trace;

  public OrderControllerConcreteProxy(OrderControllerV2 target, LogTrace trace) {
    super(null);
    this.target = target;
    this.trace = trace;
  }

  @Override
  public String request(String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderRepository.save()");
      //target 호출
      String result = target.request(itemId);
      trace.end(status);
      return result;
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }

  @Override
  public String noLog() {
    return target.noLog();
  }

}
