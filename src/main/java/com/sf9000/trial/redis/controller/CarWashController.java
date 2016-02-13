package com.sf9000.trial.redis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sf9000.trial.redis.Application;
import com.sf9000.trial.redis.domain.Car;
import com.sf9000.trial.redis.repository.CarRepo;

@RestController
@RequestMapping("/carwash")
public class CarWashController {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private CarRepo carRepo;
	
	@RequestMapping(value="/enter", method=RequestMethod.POST)
	public void enter(@RequestBody Car car){
		
		logger.debug("Entering the car " + car.getBrand() + ", registration: " + car.getRegistration());
		
		carRepo.save(car);
		
		logger.debug("Car " + car.getBrand() + ", registration: " + car.getRegistration() + " entered successfully!");
		
	}
	
	@RequestMapping(value="/allentries", method=RequestMethod.GET)
	public Iterable<Car> allEntries(){
		
		logger.debug("Fetching all entries in the car wash!");
		
		List<Car> carList = new ArrayList<>();
		
		Map<Object, Object> carMap = carRepo.findAll();
		
		
		carMap.forEach((k,v) -> carList.add((Car) v));
		
		return carList;
	}
	
	

}
