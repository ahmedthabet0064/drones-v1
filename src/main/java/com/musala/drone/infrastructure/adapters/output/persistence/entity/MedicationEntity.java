package com.musala.drone.infrastructure.adapters.output.persistence.entity;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name; //(allowed only letters, numbers, ‘-‘, ‘_’);

    private int weight;
    
    private String code; //(allowed only upper case letters, underscore and numbers);
    
    //private Blob image;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id")
    private DroneEntity drone;

}
