package example.proxy.config.v1_proxy;

import example.proxy.app.v1.OrderControllerV1;
import example.proxy.app.v1.OrderControllerV1Impl;
import example.proxy.app.v1.OrderRepositoryV1;
import example.proxy.app.v1.OrderRepositoryV1Impl;
import example.proxy.app.v1.OrderServiceV1;
import example.proxy.app.v1.OrderServiceV1Impl;
import example.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import example.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import example.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import example.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

  @Bean
  public OrderControllerV1 orderController(LogTrace trace) {
    OrderControllerV1Impl orderControllerImpl = new OrderControllerV1Impl(orderService(trace));
    return new OrderControllerInterfaceProxy(orderControllerImpl, trace);
  }

  @Bean
  public OrderServiceV1 orderService(LogTrace trace) {
    OrderServiceV1Impl orderServiceImpl = new OrderServiceV1Impl(orderRepository(trace));
    return new OrderServiceInterfaceProxy(orderServiceImpl, trace);
  }

  @Bean
  public OrderRepositoryV1 orderRepository(LogTrace trace) {
    OrderRepositoryV1Impl orderRepositoryImpl = new OrderRepositoryV1Impl();
    return new OrderRepositoryInterfaceProxy(orderRepositoryImpl, trace);
  }
}
