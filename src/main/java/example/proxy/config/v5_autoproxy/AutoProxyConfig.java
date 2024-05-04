package example.proxy.config.v5_autoproxy;

import example.proxy.config.AppV1Config;
import example.proxy.config.AppV2Config;
import example.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import example.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

  @Bean
  public Advisor advisor1(LogTrace trace) {
    //Pointcut
    NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedNames("request*", "order*", "save*");

    //Advice
    LogTraceAdvice advice = new LogTraceAdvice(trace);
    return new DefaultPointcutAdvisor(pointcut, advice);
  }

}
