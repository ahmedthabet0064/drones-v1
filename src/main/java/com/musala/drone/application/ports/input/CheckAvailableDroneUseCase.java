package com.musala.drone.application.ports.input;

import java.util.List;

import com.musala.drone.domain.model.Drone;

public interface CheckAvailableDroneUseCase {

	List<Drone> checkingAvailableDronesForLoading();
}
