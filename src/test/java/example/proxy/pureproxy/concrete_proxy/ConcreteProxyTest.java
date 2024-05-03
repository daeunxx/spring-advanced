package example.proxy.pureproxy.concrete_proxy;

import example.proxy.pureproxy.concrete_proxy.code.ConcreteClient;
import example.proxy.pureproxy.concrete_proxy.code.ConcreteLogic;
import example.proxy.pureproxy.concrete_proxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

  @Test
  void noProxy() {
    ConcreteLogic logic = new ConcreteLogic();
    ConcreteClient client = new ConcreteClient(logic);
    client.execute();
  }

  @Test
  void addProxy() {
    ConcreteLogic logic = new ConcreteLogic();
    TimeProxy timeProxy = new TimeProxy(logic);
    ConcreteClient client = new ConcreteClient(timeProxy);
    client.execute();
  }
}
