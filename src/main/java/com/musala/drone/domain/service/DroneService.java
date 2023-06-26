package com.musala.drone.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.drone.application.ports.input.CheckAvailableDroneUseCase;
import com.musala.drone.application.ports.input.CheckDroneBatteryUseCase;
import com.musala.drone.application.ports.input.CheckingLoadedMedicationUseCase;
import com.musala.drone.application.ports.input.LoadingDroneUseCase;
import com.musala.drone.application.ports.input.RegisterDroneUseCase;
import com.musala.drone.application.ports.output.DroneOutputPort;
import com.musala.drone.domain.model.Drone;
import com.musala.drone.domain.model.Medication;
import com.musala.drone.infrastructure.adapters.input.rest.data.request.DroneRequest;
import com.musala.drone.infrastructure.adapters.output.persistence.entity.DroneEntity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DroneService implements RegisterDroneUseCase,LoadingDroneUseCase
											,CheckAvailableDroneUseCase,CheckDroneBatteryUseCase,
											CheckingLoadedMedicationUseCase{

	@Autowired
	private DroneOutputPort droneOutputPort;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Drone registerDrone(Drone drone) {
		log.info("registerDrone -  DroneService ..");
		drone = droneOutputPort.createDrone(drone);
		return drone;
	}

	@Override
	public Drone getDroneWithMedicationItems(int id) {
		return  droneOutputPort.getDrone(id);
	}

	@Override
	public List<Drone> checkingAvailableDronesForLoading() {
		// TODO Auto-generated method stub
		return droneOutputPort.getListOfIdleDrone();
	}

	@Override
	public String checkDroneBatteryLevel(int id) {
		// TODO Auto-generated method stub
		int batteryLevel = droneOutputPort.getDroneBatteryLevel(id);
		String result = "Battery Level for this Drone id is "+batteryLevel+" % ";
		return result;
	}

	@Override
	public void loadDroneWithMedicationItems(int droneId,List<Medication> medications) {
		
		droneOutputPort.loadDroneWithMedication(droneId, medications);
	}

	@Override
	public List<Medication> CheckingLoadedMedicationItems(int droneId) {
		// TODO Auto-generated method stub
		Drone drone = droneOutputPort.getDrone(droneId);
		List<Medication> medications = drone.getMedications();

		return medications;
	}

}
