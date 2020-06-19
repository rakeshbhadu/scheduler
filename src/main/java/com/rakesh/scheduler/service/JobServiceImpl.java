package com.rakesh.scheduler.service;

import com.rakesh.scheduler.enums.State;
import com.rakesh.scheduler.pojo.Job;
import com.rakesh.scheduler.pojo.Task;
import lombok.Synchronized;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rakesh on 12/06/20.
 */
public class JobServiceImpl implements JobService {

    private HashMap<String, Task> taskDetails;
    private List<Job> jobs;
    private Integer jobIndex;

    public JobServiceImpl() {
        jobIndex = 0;
        jobs = new LinkedList<>();
        taskDetails = new HashMap<>();
    }

    @Override
    public Task add(Task task) {
        System.out.println("Add task " + task);
        if(taskDetails.get(task.getId()) == null){
            taskDetails.put(task.getId(), task);
            addJob(new Job(task));
        }else {
            System.out.print("Task is already present with with id: "+task.getId());
        }
        return task;
    }

    @Override
    public Task update(String taskId, State state) {
        Task task = taskDetails.get(taskId);
        if(!task.getState().equals(state)){
            task.setState(state);
            switch (state){
                case ACTIVE:
                    addJob(new Job(task));
                    break;
                case IN_ACTIVE:
                    removeJob(task.getId());
            }
        }
        return task;
    }

    @Override
    public List<Job> get(DateTime startTime, DateTime endTime) {
        List<Job> jobHistory = new ArrayList<>();
        for(Job job : jobs){
            if (job.getStartDate() != null && job.getStartDate().isBefore(endTime) &&
                    (job.getEndDate() == null || job.getEndDate().isAfter(startTime))){
                jobHistory.add(job);
            }
        }
        return jobHistory;
    }

    @Override
    public List<Task> getActiveTask() {
        List<Task> tasks = new ArrayList<>();
        for(String taskId : taskDetails.keySet()){
            if(taskDetails.get(taskId).getState().equals(State.ACTIVE)){
                tasks.add(taskDetails.get(taskId));
            }
        }
        return tasks;
    }

    @Override
    @Synchronized
    public Job getJob() {
        if(jobs.size() > 0 && jobIndex < jobs.size()) {
            Job job = jobs.get(jobIndex);
            if (job.getExpectedStartDate().isBeforeNow()) {
                if(job.getTask().isRepetitiveTask()){
                    addJob(new Job(job.getTask()));
                }
                jobIndex++;

                return job;
            }
        }
        return null;
    }

    private void addJob(Job job) {
        int i = 0;
        for (Job job1 : jobs) {
            if (job1.getExpectedStartDate().isAfter(job.getExpectedStartDate()) ||
                    (job.getExpectedStartDate().equals(job1.getExpectedStartDate()) &&
                            job.getTask().getPriority().getValue() > job1.getTask().getPriority().getValue())) {
                break;
            }
            i++;
        }
        jobs.add(i, job);
    }

    private void removeJob(String jobId){
        for(int i = jobIndex; i < jobs.size();i++){
            if(jobs.get(i).getId().equals(jobId)){
                jobs.remove(i--);
            }
        }
    }


}
