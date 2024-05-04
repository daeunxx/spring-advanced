package example.proxy.config.v6_aop.aspect;

import example.proxy.config.AppV1Config;
import example.proxy.config.AppV2Config;
import example.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AopConfig {

  @Bean
  public LogTraceAspect logTraceAspect(LogTrace trace) {
    return new LogTraceAspect(trace);
  }
}
