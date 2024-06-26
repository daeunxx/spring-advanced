package example.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//스프링은 @Controller 또는 @RestController 가 있으면 컨트롤러로 인식
@RestController
public interface OrderControllerV1 {

  @GetMapping("/v1/request")
  String request(@RequestParam("itemId") String itemId);

  @GetMapping("/v1/no-log")
  String noLog();
}
