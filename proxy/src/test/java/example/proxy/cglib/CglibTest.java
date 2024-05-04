package example.proxy.cglib;

import example.proxy.cglib.code.TimeMethodInterceptor;
import example.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.build.ToStringPlugin.Enhance;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

  @Test
  void cglib() {
    ConcreteService target = new ConcreteService();

    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(ConcreteService.class);
    enhancer.setCallback(new TimeMethodInterceptor(target));
    ConcreteService proxy = (ConcreteService) enhancer.create();

    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());

    proxy.call();
  }
}
