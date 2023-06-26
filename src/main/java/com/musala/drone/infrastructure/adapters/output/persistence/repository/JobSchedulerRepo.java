package com.musala.drone.infrastructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.drone.infrastructure.adapters.output.persistence.entity.JobSchedulerDetails;

@Repository
public interface JobSchedulerRepo extends JpaRepository<JobSchedulerDetails, Integer> {

	
}
