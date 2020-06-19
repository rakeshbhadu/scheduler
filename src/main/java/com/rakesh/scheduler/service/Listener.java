package com.rakesh.scheduler.service;

import com.rakesh.scheduler.pojo.Job;
import org.joda.time.DateTime;

/**
 * Created by rakesh on 12/06/20.
 */
public abstract class Listener {

    private JobService jobService;

    public abstract void init();

    public JobService getJobService() {
        return jobService;
    }

    Listener(JobService jobService) {
        this.jobService = jobService;
    }


}
