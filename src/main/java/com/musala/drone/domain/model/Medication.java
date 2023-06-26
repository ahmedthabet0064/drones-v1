package com.musala.drone.domain.model;

import java.sql.Blob;
import java.util.List;

import com.musala.drone.infrastructure.adapters.output.persistence.entity.DroneEntity;
import com.musala.drone.infrastructure.adapters.output.persistence.entity.MedicationEntity;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medication {

    private int id;

    private String name; //(allowed only letters, numbers, ‘-‘, ‘_’);

    private int weight;
    
    private String code; //(allowed only upper case letters, underscore and numbers);
    
//    private Blob image;
    
    private Drone drone;
}
