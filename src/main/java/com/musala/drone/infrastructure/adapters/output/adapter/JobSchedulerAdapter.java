package com.musala.drone.infrastructure.adapters.output.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.drone.application.ports.output.DroneOutputPort;
import com.musala.drone.application.ports.output.JobSchedulerPort;
import com.musala.drone.domain.model.Drone;
import com.musala.drone.infrastructure.adapters.output.persistence.entity.DroneEntity;
import com.musala.drone.infrastructure.adapters.output.persistence.entity.JobSchedulerDetails;
import com.musala.drone.infrastructure.adapters.output.persistence.repository.DroneRepository;
import com.musala.drone.infrastructure.adapters.output.persistence.repository.JobSchedulerRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class JobSchedulerAdapter  implements JobSchedulerPort {

	@Autowired
	private JobSchedulerRepo jobSAchedlerRepo;

	@Override
	public JobSchedulerDetails createJobSchedulerDetails(JobSchedulerDetails jobSchedulerDetails) {
		log.info("createDrone -  DronePersistenceAdapter ..");

		try {
			JobSchedulerDetails job = jobSAchedlerRepo.save(jobSchedulerDetails);
			return job;
		} catch (Exception e) {
			log.error("Error saving loanRequestEntity Object", e.getMessage());
			throw e;
		}

	}
}
