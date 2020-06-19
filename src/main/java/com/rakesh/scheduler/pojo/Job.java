package com.rakesh.scheduler.pojo;

import com.rakesh.scheduler.enums.ResultType;
import com.rakesh.scheduler.enums.State;
import com.rakesh.scheduler.enums.TaskType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

/**
 * Created by rakesh on 12/06/20.
 */
@Getter
@Setter
@ToString
public class Job implements Runnable {

    private String id;

    private DateTime expectedStartDate;

    private DateTime startDate;

    private DateTime endDate;

    private ResultType resultType;

    private Task task;

    public Job(Task task) {
        id = task.getId();
        this.expectedStartDate = task.expectedStartDate();
        this.task = task;
    }

    @Override
    public void run() {
        try {
            System.out.println("Running new job " + this+ " at " + Thread.currentThread().getName());
            startDate = DateTime.now();
            Thread.sleep(task.getTaskDuration());
            endDate = DateTime.now();
            System.out.println("Completed the job " + this+ " at " + Thread.currentThread().getName());

            if (task.getTaskType() == TaskType.A && task.getState() == State.ACTIVE) {
                task.setState(State.COMPLETED);
            }

            resultType = ResultType.SUCCESS;
        } catch (InterruptedException e) {
            e.printStackTrace();
            resultType = ResultType.FAILURE;
        }
    }
}
