package example.proxy.proxyfactory;

import static org.assertj.core.api.Assertions.assertThat;

import example.proxy.common.advice.TimeAdvice;
import example.proxy.common.service.ConcreteService;
import example.proxy.common.service.ServiceImpl;
import example.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class ProxyFactoryTest {

  @Test
  @DisplayName("인터페이스가 존재하면 JDK 동적 프록시 사용")
  void interfaceProxy() {
    ServiceInterface target = new ServiceImpl();

    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());

    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());

    proxy.save();

    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
    assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
  }

  @Test
  @DisplayName("구체 클래스가 존재하면 CGLIB 사용")
  void concreteProxy() {
    ConcreteService target = new ConcreteService();

    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());

    ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());

    proxy.call();

    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
    assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
  }

  @Test
  @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 존재해도 CGLIB 사용")
  void proxyTargetTest() {
    ServiceInterface target = new ServiceImpl();

    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.addAdvice(new TimeAdvice());
    proxyFactory.setProxyTargetClass(true);

    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());

    proxy.save();

    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
    assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
  }
}
