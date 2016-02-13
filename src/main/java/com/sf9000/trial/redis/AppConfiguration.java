package com.sf9000.trial.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sf9000.trial.redis.controller.CarWashController;
import com.sf9000.trial.redis.domain.Car;
import com.sf9000.trial.redis.repository.CarRepo;
import com.sf9000.trial.redis.repository.CarRepoImpl;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@ComponentScan(basePackageClasses = CarWashController.class)
@Configuration
@EnableAutoConfiguration
@EnableSwagger2
public class AppConfiguration extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AppConfiguration.class);
	
	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Car> redisTemplate(RedisConnectionFactory connectionFactory){
		logger.debug("Getting redisTemplate bean.");
		
		RedisTemplate<String, Car> redisTemplate = new RedisTemplate<>();
		
		redisTemplate.setConnectionFactory(connectionFactory);
		
		return redisTemplate;
	}
	
	@Bean(name = "carRepo")
	public CarRepo carRepo(){
		logger.debug("Getting carRepo bean.");
		return new CarRepoImpl();
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }	
	
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.regex("/api/.*"))
            .build()
            .apiInfo(ApiInfo.DEFAULT);
    }	

}
