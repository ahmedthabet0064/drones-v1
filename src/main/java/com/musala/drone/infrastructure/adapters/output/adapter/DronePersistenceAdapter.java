package com.musala.drone.infrastructure.adapters.output.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.musala.drone.application.ports.output.DroneOutputPort;
import com.musala.drone.domain.model.Drone;
import com.musala.drone.domain.model.Medication;
import com.musala.drone.domain.model.enums.DroneStateEnum;
import com.musala.drone.infrastructure.adapters.output.exception.BusinessException;
import com.musala.drone.infrastructure.adapters.output.persistence.entity.DroneEntity;
import com.musala.drone.infrastructure.adapters.output.persistence.entity.MedicationEntity;
import com.musala.drone.infrastructure.adapters.output.persistence.repository.DroneRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class DronePersistenceAdapter implements DroneOutputPort {

	@Autowired
	private DroneRepository droneRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Drone createDrone(Drone drone) {

		log.info("createDrone -  DronePersistenceAdapter ..");

		try {
			DroneEntity droneEntity = modelMapper.map(drone, DroneEntity.class);
			droneEntity = droneRepository.save(droneEntity);
			return modelMapper.map(droneEntity, Drone.class);
		} catch (Exception e) {
			log.error("Error saving loanRequestEntity Object", e.getMessage());
			throw e;
		}

	}

	@Override
	public Drone getDrone(int id) {

		Optional<DroneEntity> droneEntityOptional = droneRepository.findById(id);
		if (droneEntityOptional.isPresent()) {

			// return modelMapper.map(droneEntity.get(), Drone.class);
			DroneEntity droneEntity = droneEntityOptional.get();
			Drone drone = new Drone();
			drone.setId(droneEntity.getId());
			drone.setSerialNumber(droneEntity.getSerialNumber());
			drone.setModel(droneEntity.getModel());
			drone.setWeightLimit(droneEntity.getWeightLimit());
			drone.setBatteryCapacity(droneEntity.getBatteryCapacity());
			drone.setState(droneEntity.getState());
			List<Medication> medicationList = new ArrayList<>();
			for (int i = 0; i < droneEntity.getMedications().size(); i++) {
				Medication medication = new Medication();
				medication.setId(droneEntity.getMedications().get(i).getId());
				medication.setName(droneEntity.getMedications().get(i).getName());
				medication.setCode(droneEntity.getMedications().get(i).getCode());
				medication.setWeight(droneEntity.getMedications().get(i).getWeight());
				medicationList.add(medication);
			}
			drone.setMedications(medicationList);
			return drone;
		} else
			log.error("Error getting drone Object");
		throw new BusinessException(HttpStatus.CONFLICT, "Error Get drone Data", "Drone_002");
	}

	@Override
	public List<Drone> getListOfIdleDrone() {
		// TODO Auto-generated method stub
		List<DroneEntity> drones = droneRepository.findAll();
		List<Drone> droneList = new ArrayList<>();
		if (drones != null) {
			for (int i = 0; i < drones.size(); i++) {
				Drone drone = new Drone();
				drone.setId(drones.get(i).getId());
				drone.setSerialNumber(drones.get(i).getSerialNumber());
				drone.setModel(drones.get(i).getModel());
				drone.setWeightLimit(drones.get(i).getWeightLimit());
				drone.setBatteryCapacity(drones.get(i).getBatteryCapacity());
				drone.setState(drones.get(i).getState());
				if (drones.get(i).getState().equalsIgnoreCase(DroneStateEnum.IDLE.name())) {
					droneList.add(drone);
				}

			}
			return droneList;

		}
		log.error("Error getting drone Object");
		return new ArrayList<Drone>();
	}

	@Override
	public int getDroneBatteryLevel(int id) {
		Optional<DroneEntity> droneEntityOptional = droneRepository.findById(id);
		if (droneEntityOptional.isPresent()) {
			DroneEntity droneEntity = droneEntityOptional.get();
			return droneEntity.getBatteryCapacity();
		} else
			log.error("Error getting drone Object");
		throw new BusinessException(HttpStatus.CONFLICT, "Error Drone Id not exist", "Drone_003");
	}

	@Override
	public List<Medication> getAllLoadedMedication(int droneId) {
		Optional<DroneEntity> droneEntityOptional = droneRepository.findById(droneId);
		if (droneEntityOptional.isPresent()) {

			DroneEntity droneEntity = droneEntityOptional.get();

			List<Medication> medicationList = new ArrayList<>();
			for (int i = 0; i < droneEntity.getMedications().size(); i++) {
				Medication medication = new Medication();
				medication.setId(droneEntity.getMedications().get(i).getId());
				medication.setName(droneEntity.getMedications().get(i).getName());
				medication.setCode(droneEntity.getMedications().get(i).getCode());
				medication.setWeight(droneEntity.getMedications().get(i).getWeight());
				medicationList.add(medication);
			}
			return medicationList;
		} else
			log.error("Error getting drone Object");
		throw new BusinessException(HttpStatus.CONFLICT, "Error Get medication  Data", "Drone_003");
	}

	@Override
	public void loadDroneWithMedication(int id, List<Medication> medications) {

		Optional<DroneEntity> droneEntityOptional = droneRepository.findById(id);
		if (droneEntityOptional.isPresent()) {

			// return modelMapper.map(droneEntity.get(), Drone.class);
			DroneEntity droneEntity = droneEntityOptional.get();
			List<MedicationEntity> medicationList = new ArrayList<>();
			for (int i = 0; i < medications.size(); i++) {
				MedicationEntity medicationEntity = new MedicationEntity();
				medicationEntity.setId(medications.get(i).getId());
				medicationEntity.setName(medications.get(i).getName());
				medicationEntity.setCode(medications.get(i).getCode());
				medicationEntity.setWeight(medications.get(i).getWeight());
				medicationList.add(medicationEntity);
			}
			droneEntity.setMedications(medicationList);
			droneEntity.setState(DroneStateEnum.LOADING.name());
			if (droneEntity.getBatteryCapacity() <= 25) {
				throw new BusinessException(HttpStatus.CONFLICT,
						"Error Saving Medication becouse battery level of drone" + "less than 25% ", "Medication_00");
			} else {
				droneRepository.save(droneEntity);
			}
		} else
			log.error("Error getting drone Object");
		throw new BusinessException(HttpStatus.CONFLICT, "Error Saving Medication ", "Medication_002");

	}

	@Override
	public List<Drone> getListOfAllDrone() {
		List<DroneEntity> drones = droneRepository.findAll();
		List<Drone> droneList = new ArrayList<>();
		if (drones != null) {
			for (int i = 0; i < drones.size(); i++) {
				Drone drone = new Drone();
				drone.setId(drones.get(i).getId());
				drone.setSerialNumber(drones.get(i).getSerialNumber());
				drone.setModel(drones.get(i).getModel());
				drone.setWeightLimit(drones.get(i).getWeightLimit());
				drone.setBatteryCapacity(drones.get(i).getBatteryCapacity());
				drone.setState(drones.get(i).getState());
				droneList.add(drone);
			}
			return droneList;

		}
		log.error("Error getting drone Object");
		return new ArrayList<Drone>();
	}

}
