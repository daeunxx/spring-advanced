package example.proxy.config.v1_proxy.interface_proxy;

import example.proxy.app.v1.OrderControllerV1;
import example.proxy.trace.TraceStatus;
import example.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

  private final OrderControllerV1 target;
  private final LogTrace trace;

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
