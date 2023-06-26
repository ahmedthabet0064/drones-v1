package com.musala.drone.infrastructure.adapters.input.rest.data.request;

import com.musala.drone.domain.model.Drone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DroneRequest {
	
	private String serialNumber;

	private String model;

	private int weightLimit;

	private int batteryCapacity;

	private String state;

}
