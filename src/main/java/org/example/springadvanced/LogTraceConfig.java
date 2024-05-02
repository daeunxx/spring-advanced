package org.example.springadvanced;

import org.example.springadvanced.trace.logtrace.FieldLogTrace;
import org.example.springadvanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

  @Bean
  public LogTrace logTrace() {
    return new FieldLogTrace();
  }
}
