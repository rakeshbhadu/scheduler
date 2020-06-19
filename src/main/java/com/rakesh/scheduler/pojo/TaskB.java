package com.rakesh.scheduler.pojo;

import com.rakesh.scheduler.enums.State;
import com.rakesh.scheduler.enums.Priority;
import com.rakesh.scheduler.enums.TaskType;
import com.rakesh.scheduler.utils.CronUtils;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;


/**
 * Created by rakesh on 11/06/20.
 */
@Getter
@ToString
public class TaskB extends Task {

    private String cronExpression;

    public TaskB(String id, Priority priority, State status, TaskType taskType, Long taskDuration,
                 String cronExpression) {
        super(id, priority, status, taskType, taskDuration);
        String[] split = cronExpression.split(" ");

        this.cronExpression = "* " +
                split[0] + " " +
                split[1] + " " +
                "? * " +
                split[2] +
                " *";
    }

    @Override
    public DateTime expectedStartDate() {
        return CronUtils.getNextExpectedTime(cronExpression);
    }

    @Override
    public Boolean isRepetitiveTask(){ return true;}


}
