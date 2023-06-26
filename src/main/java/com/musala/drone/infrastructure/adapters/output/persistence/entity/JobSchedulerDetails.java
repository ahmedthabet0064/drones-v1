package com.musala.drone.infrastructure.adapters.output.persistence.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSchedulerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String jobSchedulerName;
	private String status;// (success-failure),
	private String StatusMessage;
	private String StatusDetail;// (empty in success),
	private String durationTime;
	private int batteryLevel;

}
