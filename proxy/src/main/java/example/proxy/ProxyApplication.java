package example.proxy;

import example.proxy.config.v6_aop.aspect.AopConfig;
import example.proxy.trace.logtrace.LogTrace;
import example.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import({AppV1Config.class, AppV2Config.class})
//@Import({InterfaceProxyConfig.class, ConcreteProxyConfig.class})
//@Import({DynamicProxyBasicConfig.class})
//@Import({DynamicProxyFilterConfig.class})
//@Import({ProxyFactoryConfigV1.class, ProxyFactoryConfigV2.class})
//@Import({BeanPostProcessorConfig.class})
//@Import({AutoProxyConfig.class})
@Import({AopConfig.class})
@SpringBootApplication(scanBasePackages = "example.proxy.app.v3") //주의
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}

}
