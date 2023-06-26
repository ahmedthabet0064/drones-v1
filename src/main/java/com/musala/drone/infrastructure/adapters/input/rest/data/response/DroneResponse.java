package com.musala.drone.infrastructure.adapters.input.rest.data.response;

import java.util.List;

import com.musala.drone.domain.model.Medication;
import com.musala.drone.infrastructure.adapters.input.rest.data.request.DroneRequest;
import com.musala.drone.infrastructure.adapters.output.persistence.entity.MedicationEntity;

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
public class DroneResponse {

	private int id;

	private String serialNumber;

	private String model;

	private int weightLimit;

	private int batteryCapacity;

	private String state;
	
	private List<Medication> medications;
}
