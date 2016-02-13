package com.sf9000.trial.redis.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.sf9000.trial.redis.domain.Car;

public class CarRepoImpl implements CarRepo {

	@Autowired
	public RedisTemplate<String, Car> redisTemplate;
	
	private static String CAR_KEY = "Car";

	@Override
	public void save(Car car) {
		this.redisTemplate.opsForHash().put(CAR_KEY, car.getRegistration(), car);
	}

	@Override
	public Car find(String id) {
		return (Car) this.redisTemplate.opsForHash().get(CAR_KEY, id);
	}

	@Override
	public Map<Object, Object> findAll() {
		return this.redisTemplate.opsForHash().entries(CAR_KEY);
	}

	@Override
	public void delete(String id) {
		this.redisTemplate.opsForHash().delete(CAR_KEY, id);
	}
	
}
