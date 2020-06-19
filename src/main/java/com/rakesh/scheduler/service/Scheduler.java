package com.rakesh.scheduler.service;

import com.rakesh.scheduler.enums.State;
import com.rakesh.scheduler.enums.TaskType;
import com.rakesh.scheduler.pojo.Job;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by rakesh on 11/06/20.
 */
public class Scheduler {
    private static int THREAD_POOL_COUNT = 2;
    private JobService jobService;
    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_POOL_COUNT);

    public class Ping implements Runnable {
        @Override
        public void run() {
//            System.out.println("Pinging for new Job");
            try {
                if (executor.getActiveCount() < THREAD_POOL_COUNT) {
                    Job job = jobService.getJob();
                    if (null != job) {
                        System.out.println("Found new job " + job);
                        executor.execute(job);
                    }
                }
            } catch (Exception e){
                System.out.println("Failed ping");
            }
        }
    }

    public Scheduler(JobService jobService) {
        this.jobService = jobService;
        ScheduledExecutorService pingExecutor = Executors.newScheduledThreadPool(1);
        pingExecutor.scheduleWithFixedDelay(new Ping(), 0L, 1L, TimeUnit.SECONDS);
    }

}

