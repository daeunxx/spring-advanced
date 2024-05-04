package example.proxy.config.v2_dynamic_proxy;

import example.proxy.app.v1.OrderControllerV1;
import example.proxy.app.v1.OrderControllerV1Impl;
import example.proxy.app.v1.OrderRepositoryV1;
import example.proxy.app.v1.OrderRepositoryV1Impl;
import example.proxy.app.v1.OrderServiceV1;
import example.proxy.app.v1.OrderServiceV1Impl;
import example.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class DynamicProxyBasicConfig {

  @Bean
  public OrderControllerV1 orderControllerV1(LogTrace trace) {
    OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(trace));
    return (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
        new Class[]{OrderControllerV1.class},
        new LogTraceBasicHandler(orderControllerV1, trace));
  }

  @Bean
  public OrderServiceV1 orderServiceV1(LogTrace trace) {
    OrderServiceV1Impl orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(trace));
    return (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
        new Class[]{OrderServiceV1.class},
        new LogTraceBasicHandler(orderServiceV1, trace));
  }

  @Bean
  public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
    OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
    return (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
        new Class[]{OrderRepositoryV1.class},
        new LogTraceBasicHandler(orderRepository, trace));
  }
}
