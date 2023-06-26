package com.musala.drone.infrastructure.adapters.input.rest.adapter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musala.drone.application.ports.input.CheckAvailableDroneUseCase;
import com.musala.drone.application.ports.input.CheckDroneBatteryUseCase;
import com.musala.drone.application.ports.input.CheckingLoadedMedicationUseCase;
import com.musala.drone.application.ports.input.LoadingDroneUseCase;
import com.musala.drone.application.ports.input.RegisterDroneUseCase;
import com.musala.drone.domain.model.Drone;
import com.musala.drone.domain.model.Medication;
import com.musala.drone.infrastructure.adapters.input.rest.data.request.DroneRequest;
import com.musala.drone.infrastructure.adapters.input.rest.data.response.DroneResponse;
import com.musala.drone.infrastructure.adapters.output.persistence.entity.DroneEntity;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/drone")
@Slf4j
public class DroneRestAdapter {

	@Autowired
	RegisterDroneUseCase registerDroneUseCase;

	@Autowired
	LoadingDroneUseCase loadingDroneUseCase;
	
	@Autowired
	CheckAvailableDroneUseCase checkAvailableDroneUseCase;
	
	@Autowired
	CheckDroneBatteryUseCase checkDroneBatteryUseCase;
	
	@Autowired
	CheckingLoadedMedicationUseCase checkingLoadedMedicationUseCase;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/register")
	public ResponseEntity<DroneResponse> registerDrone(@RequestBody DroneRequest droneRequest) {
		
		log.info("registerDrone - DroneRestAdapter Controller Function");
		
		Drone drone =  modelMapper.map(droneRequest, Drone.class);
		
		drone = registerDroneUseCase.registerDrone(drone);
		
		DroneResponse droneResponse =  modelMapper.map(drone, DroneResponse.class);
	
		return new ResponseEntity<>(droneResponse, HttpStatus.OK);
	}
	
	@PostMapping("/loadDroneWithMedications")
	public ResponseEntity<String> loadingDroneMedication(@RequestParam(name = "id") int id,@RequestBody List<Medication> medications) {
		
		log.info("checkDroneBatteryUseCase - DroneRestAdapter Controller Function");
				
		loadingDroneUseCase.loadDroneWithMedicationItems(id,medications);
		String message = "loaded Successuflly";
	
		return new ResponseEntity<>(message, HttpStatus.OK);
}
	
	@GetMapping("/getDrone")
	public ResponseEntity<DroneResponse> getDrone(@RequestParam(name = "id") int id) {
		
		log.info("loadDrone - DroneRestAdapter Controller Function");
				
		Drone drone = loadingDroneUseCase.getDroneWithMedicationItems(id);
		
		DroneResponse droneResponse =  modelMapper.map(drone, DroneResponse.class);
	
		return new ResponseEntity<>(droneResponse, HttpStatus.OK);
	}
	

	@GetMapping("/loadDroneWithMedication")
	public ResponseEntity<DroneResponse> loadDrone(@RequestParam(name = "id") int id) {
		
		log.info("loadDrone - DroneRestAdapter Controller Function");
				
		Drone drone = loadingDroneUseCase.getDroneWithMedicationItems(id);
		
		DroneResponse droneResponse =  modelMapper.map(drone, DroneResponse.class);
	
		return new ResponseEntity<>(droneResponse, HttpStatus.OK);
	}
	
	
	@GetMapping("/checkingLoadedMedication")
	public ResponseEntity<List<Medication>> checkingLoadedMedication(@RequestParam(name = "droneId") int droneId) {
		
		log.info("checkingLoadedMedication - DroneRestAdapter Controller Function");
				
		List<Medication> medications = checkingLoadedMedicationUseCase.CheckingLoadedMedicationItems(droneId);
		
	
		return new ResponseEntity<>(medications, HttpStatus.OK);
}
	

	@GetMapping("/loadDroneAvailableForLoading")
	public ResponseEntity<List<Drone>> loadDroneAvailable() {
		
		log.info("loadDroneAvailableForLoading - DroneRestAdapter Controller Function");
				
		List<Drone> drones = checkAvailableDroneUseCase.checkingAvailableDronesForLoading();
		
	
		return new ResponseEntity<>(drones, HttpStatus.OK);
}
	@GetMapping("/checkDroneBatteryUseCase")
	public ResponseEntity<String> checkDroneBatteryCapacity(@RequestParam(name = "id") int id) {
		
		log.info("checkDroneBatteryUseCase - DroneRestAdapter Controller Function");
				
		String res = checkDroneBatteryUseCase.checkDroneBatteryLevel(id);
		
	
		return new ResponseEntity<>(res, HttpStatus.OK);
}
	



}
