package com.musala.drone.infrastructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.drone.infrastructure.adapters.output.persistence.entity.MedicationEntity;

@Repository
public interface MedicationRepository extends JpaRepository<MedicationEntity, Long> {

}
