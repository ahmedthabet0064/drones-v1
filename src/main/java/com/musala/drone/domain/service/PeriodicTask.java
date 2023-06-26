package com.musala.drone.domain.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.musala.drone.application.ports.output.DroneOutputPort;
import com.musala.drone.domain.model.Drone;
import com.musala.drone.domain.model.enums.JobSchedulerDetailsStatus;
import com.musala.drone.infrastructure.adapters.output.adapter.JobSchedulerAdapter;
import com.musala.drone.infrastructure.adapters.output.exception.BusinessException;
import com.musala.drone.infrastructure.adapters.output.persistence.entity.JobSchedulerDetails;

import lombok.extern.slf4j.Slf4j;

@Component
@EnableScheduling
@Slf4j
public class PeriodicTask {
    

    @Autowired
    JobSchedulerAdapter jobSchedulerAdapter;
    
    @Autowired
    DroneOutputPort droneOutputPort;

    @Scheduled(cron = "${cron.expression.endofday}")
    public void endOfDayExecution(){
        long startTime = System.currentTimeMillis();
        long endTime =0L;
        log.info("Start endOfDateExecution Scheduler  [{}] msec", (startTime));

        //in try catch

        JobSchedulerDetails jobSchedulerDetails = new JobSchedulerDetails();
        List<Drone> drones = droneOutputPort.getListOfAllDrone();
        if(drones !=null) {
        for (Drone drone : drones) {
            try {
            	
                jobSchedulerDetails.setStatus(JobSchedulerDetailsStatus.SUCCESS.name());
                jobSchedulerDetails.setStatusMessage("Job run Successfully");
                jobSchedulerDetails.setBatteryLevel(drone.getBatteryCapacity());
                jobSchedulerDetails.setJobSchedulerName("Audit-Job");

            } catch (Exception e) {
                jobSchedulerDetails.setStatus(JobSchedulerDetailsStatus.FAILURE.name());
                jobSchedulerDetails.setStatusMessage(e.getMessage());
                jobSchedulerDetails.setStatusDetail(e.getStackTrace().toString());
            }finally{
                endTime = (System.currentTimeMillis() - startTime);
                jobSchedulerDetails.setDurationTime(Long.toString(endTime)+" msec");

                jobSchedulerAdapter.createJobSchedulerDetails(jobSchedulerDetails);
            }
        }
		}else {
			log.error("Error getting drone Data");
		throw new BusinessException(HttpStatus.CONFLICT, "Error Get drone Data", "Drone_002");
		}
                
        //jobSchedulerDetailsAdapter.createJobSchedulerDetails(null);
        //log.info("End endOfDateExecution Scheduler after [{}] msec", (System.currentTimeMillis() - startTime));
    }

}
