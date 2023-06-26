package com.musala.drone.application.ports.input;

import java.util.List;

import com.musala.drone.domain.model.Drone;
import com.musala.drone.domain.model.Medication;

public interface LoadingDroneUseCase {

	Drone getDroneWithMedicationItems(int id);
	
	void loadDroneWithMedicationItems(int id, List<Medication> medications);

}
