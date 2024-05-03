package org.example.logtrace;

import org.example.logtrace.trace.logtrace.LogTrace;
import org.example.logtrace.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

  @Bean
  public LogTrace logTrace() {
    return new ThreadLocalLogTrace();
  }
}
