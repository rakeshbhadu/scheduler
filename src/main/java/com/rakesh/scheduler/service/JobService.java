package com.rakesh.scheduler.service;


import com.rakesh.scheduler.enums.State;
import com.rakesh.scheduler.pojo.Job;
import com.rakesh.scheduler.pojo.Task;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by rakesh on 12/06/20.
 */
public interface JobService {

    Task add(Task task);

    Task update(String taskId, State state);

    Job getJob();

//    void postCompletionOfJob(Job job);

    List<Job> get(DateTime startTime, DateTime endTime);

    List<Task> getActiveTask();
}
