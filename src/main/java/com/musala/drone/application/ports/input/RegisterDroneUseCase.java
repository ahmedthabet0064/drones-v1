package com.musala.drone.application.ports.input;

import com.musala.drone.domain.model.Drone;

public interface RegisterDroneUseCase {
	
	Drone registerDrone(Drone drone);

}
