package example.proxy.pureproxy.concrete_proxy.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcreteClient {

  private final ConcreteLogic logic;

  public void execute() {
    logic.operation();
  }
}
