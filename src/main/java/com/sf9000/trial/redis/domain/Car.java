package com.sf9000.trial.redis.domain;

import java.io.Serializable;

public class Car implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String brand;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + "]";
	}
	
	

}
