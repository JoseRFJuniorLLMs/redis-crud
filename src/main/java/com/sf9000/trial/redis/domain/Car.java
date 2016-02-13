package com.sf9000.trial.redis.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("registration")
	private String registration;
	
	@JsonProperty("brand")
	private String brand;
	
	@JsonProperty("dateTime")
	private Date dateTime;

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Car [registration=" + registration + ", brand=" + brand + ", dateTime=" + dateTime + "]";
	}
	

}
