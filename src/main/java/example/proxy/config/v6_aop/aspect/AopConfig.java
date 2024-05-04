package example.proxy.config.v6_aop.aspect;

import example.proxy.config.AppV1Config;
import example.proxy.config.AppV2Config;
import example.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AopConfig {

  @Bean
  public LogTraceAspect logTraceAspect(LogTrace trace) {
    log.info("LogTraceAspect Bean 생성");
    return new LogTraceAspect(trace);
  }
}
