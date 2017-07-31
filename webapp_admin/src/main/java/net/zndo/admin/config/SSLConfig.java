package net.zndo.admin.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SSL 配置
 * 
 * @author zndo
 *
 */
@Configuration
public class SSLConfig {

	/**
	 * 监听 80 端口
	 * 
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		UndertowEmbeddedServletContainerFactory undertowFactory = new UndertowEmbeddedServletContainerFactory();
		undertowFactory.addBuilderCustomizers(builder -> builder.addHttpListener(80, "0.0.0.0"));
		return undertowFactory;
	}
	
}
