package example.proxy.pureproxy.proxy.code;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProxyPatternClient {

  private Subject subject;

  public void execute() {
    subject.operation();
  }
}
