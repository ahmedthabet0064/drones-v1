package com.musala.drone.application.ports.input;

import java.util.List;

import com.musala.drone.domain.model.Medication;

public interface CheckingLoadedMedicationUseCase {
	List<Medication> CheckingLoadedMedicationItems(int droneId);
}
