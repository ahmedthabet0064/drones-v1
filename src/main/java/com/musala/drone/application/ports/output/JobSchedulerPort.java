package com.musala.drone.application.ports.output;

import com.musala.drone.infrastructure.adapters.output.persistence.entity.JobSchedulerDetails;

public interface JobSchedulerPort {

	public JobSchedulerDetails createJobSchedulerDetails(JobSchedulerDetails jobSchedulerDetails);

}
