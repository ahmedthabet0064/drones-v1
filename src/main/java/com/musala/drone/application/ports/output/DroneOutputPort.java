package com.musala.drone.application.ports.output;

import java.util.List;
import java.util.Optional;

import com.musala.drone.domain.model.Drone;
import com.musala.drone.domain.model.Medication;


public interface DroneOutputPort {
	
	public Drone createDrone(Drone drone);
	
	public Drone getDrone(int id);
	
	public void loadDroneWithMedication(int id,List<Medication> medications);
	
	public List<Drone> getListOfIdleDrone();
	
	public List<Drone> getListOfAllDrone();
	
	public int getDroneBatteryLevel(int id);
	
	public List<Medication> getAllLoadedMedication(int droneId);

}
