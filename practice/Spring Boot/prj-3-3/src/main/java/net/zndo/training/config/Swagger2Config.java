package net.zndo.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Swagger2 构建 RESTful API")
				.description("描述")
				.termsOfServiceUrl("http://zndo.live")
				.version("1.1.1")
				.build();
	}

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("net.zndo.training"))
				.paths(PathSelectors.any())
				.build();
	}

}
