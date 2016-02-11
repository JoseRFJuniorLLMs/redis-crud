package com.sf9000.trial.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.sf9000.trial.redis.domain.Car;
import com.sf9000.trial.redis.repository.CarRepo;
import com.sf9000.trial.redis.repository.CarRepoImpl;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Bean
	public CarRepo carRepo(){
		logger.info("Getting car repository.");
		return new CarRepoImpl();
	}
	
	@Autowired
	private CarRepo carRepo;
	
	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Car> redisTemplate(RedisConnectionFactory connectionFactory){
		logger.info("Getting redis template.");
		
		RedisTemplate<String, Car> redisTemplate = new RedisTemplate<>();
		
		redisTemplate.setConnectionFactory(connectionFactory);
//		redisTemplate.setDefaultSerializer(new StringRedisSerializer());
		
		return redisTemplate;
	}
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);

		Car car1 = new Car();
		car1.setId("car2");
		car1.setBrand("Mini");

//		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		logger.info("Getting carRepo bean.");
//		CarRepo carRepo = (CarRepo) ctx.getBean("carRepo");
		
		
		
		logger.info("saving car.");
		carRepo.save(car1);

	}

}
