package com.sf9000.trial.redis.repository;

import java.util.Map;

import com.sf9000.trial.redis.domain.Car;

public interface CarRepo {

	public void save(Car car);

	public Car find(String id);

	public Map<Object, Object> findAll();

	public void delete(String id);

}
