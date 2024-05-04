package example.proxy.pureproxy.proxy;

import example.proxy.pureproxy.proxy.code.CacheProxy;
import example.proxy.pureproxy.proxy.code.ProxyPatternClient;
import example.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

  @Test
  void noProxyTest() {
    RealSubject subject = new RealSubject();
    ProxyPatternClient client = new ProxyPatternClient(subject);
    client.execute();
    client.execute();
    client.execute();
  }

  @Test
  void cacheProxyTest() {
    RealSubject subject = new RealSubject();
    CacheProxy cacheProxy = new CacheProxy(subject);
    ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
    client.execute();
    client.execute();
    client.execute();
  }
}
