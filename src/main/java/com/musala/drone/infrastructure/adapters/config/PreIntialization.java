package com.musala.drone.infrastructure.adapters.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.musala.drone.application.ports.output.DroneOutputPort;
import com.musala.drone.domain.model.enums.DroneStateEnum;
import com.musala.drone.infrastructure.adapters.output.persistence.entity.DroneEntity;
import com.musala.drone.infrastructure.adapters.output.persistence.entity.MedicationEntity;
import com.musala.drone.infrastructure.adapters.output.persistence.repository.DroneRepository;
import com.musala.drone.infrastructure.adapters.output.persistence.repository.MedicationRepository;

@Configuration
public class PreIntialization implements CommandLineRunner {

	@Autowired
	private DroneRepository droneRepository;
	
	@Autowired
	private MedicationRepository mdicationRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
        DroneEntity droneEntity = new DroneEntity();
        droneEntity.setSerialNumber("ttttt-ssss-ffff");
        droneEntity.setModel("Lightweight");
        droneEntity.setWeightLimit(350);
        droneEntity.setBatteryCapacity(60);
        droneEntity.setState(DroneStateEnum.IDLE.name());
        droneEntity = droneRepository.save(droneEntity);
        
        List<MedicationEntity> MedicationEntities = new ArrayList<>();
        MedicationEntity medicationEntity1 = new MedicationEntity();
        medicationEntity1.setName("medication1");
        medicationEntity1.setCode("CODE-11");
        medicationEntity1.setWeight(300);
        medicationEntity1.setDrone(droneEntity);
        MedicationEntities.add(medicationEntity1);
        MedicationEntity medicationEntity2 = new MedicationEntity();
        medicationEntity2.setName("medication2");
        medicationEntity2.setCode("CODE-12");
        medicationEntity2.setWeight(200);
        medicationEntity2.setDrone(droneEntity);
        MedicationEntities.add(medicationEntity2);
        MedicationEntity medicationEntity3 = new MedicationEntity();
        medicationEntity3.setName("medication3");
        medicationEntity3.setCode("CODE-13");
        medicationEntity3.setWeight(100);
        medicationEntity3.setDrone(droneEntity);
        MedicationEntities.add(medicationEntity3);
       
        mdicationRepository.saveAll(MedicationEntities);
	}

}
