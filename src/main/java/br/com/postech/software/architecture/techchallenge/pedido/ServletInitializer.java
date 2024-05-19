package br.com.postech.software.architecture.techchallenge.pedido;

import br.com.postech.software.architecture.techchallenge.pedido.configuration.ParameterConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
@EnableAutoConfiguration
@EntityScan(basePackages = { ParameterConfig.PACKAGE_MODEL })
@EnableJpaRepositories(basePackages = { ParameterConfig.PACKAGE_REPOSITORY_JPA })
@ComponentScan(basePackages = {ParameterConfig.PACKAGE_CONTROLLERS,
							   ParameterConfig.PACKAGE_CONFIGURATION,
							   ParameterConfig.PACKAGE_HANDLER,
							   ParameterConfig.PACKAGE_SERVICE,
							   ParameterConfig.PACKAGE_REPOSITORY_JDBC})
public class ServletInitializer extends SpringBootServletInitializer {

	
	public ServletInitializer() {
		super();
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TechChallengeApplication.class);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
