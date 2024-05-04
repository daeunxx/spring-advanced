package example.proxy.config.v3_proxyfactory;

import example.proxy.app.v1.OrderControllerV1;
import example.proxy.app.v1.OrderControllerV1Impl;
import example.proxy.app.v1.OrderRepositoryV1;
import example.proxy.app.v1.OrderRepositoryV1Impl;
import example.proxy.app.v1.OrderServiceV1;
import example.proxy.app.v1.OrderServiceV1Impl;
import example.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import example.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV1 {

  @Bean
  public OrderControllerV1 orderControllerV1(LogTrace trace) {
    OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1(trace));

    ProxyFactory proxyFactory = new ProxyFactory(orderController);
    proxyFactory.addAdvisor(getAdvisor(trace));

    OrderControllerV1 proxy = (OrderControllerV1) proxyFactory.getProxy();
    log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderController.getClass());

    return proxy;
  }

  @Bean
  public OrderServiceV1 orderServiceV1(LogTrace trace) {
    OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(trace));

    ProxyFactory proxyFactory = new ProxyFactory(orderService);
    proxyFactory.addAdvisor(getAdvisor(trace));

    OrderServiceV1 proxy = (OrderServiceV1) proxyFactory.getProxy();
    log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderService.getClass());

    return proxy;
  }

  @Bean
  public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
    OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

    ProxyFactory proxyFactory = new ProxyFactory(orderRepository);
    proxyFactory.addAdvisor(getAdvisor(trace));

    OrderRepositoryV1 proxy = (OrderRepositoryV1) proxyFactory.getProxy();
    log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), orderRepository.getClass());

    return proxy;
  }

  private Advisor getAdvisor(LogTrace trace) {
    //Pointcut
    NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedNames("request*", "order*", "save*");

    //Advice
    LogTraceAdvice advice = new LogTraceAdvice(trace);
    return new DefaultPointcutAdvisor(pointcut, advice);
  }
}
