package com.rakesh.scheduler.pojo;

import com.rakesh.scheduler.enums.State;
import com.rakesh.scheduler.enums.Priority;
import com.rakesh.scheduler.enums.TaskType;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;


@Getter
@ToString
public class TaskA extends Task {

    private DateTime startTimestamp;

    public TaskA(String id, Priority priority, State status, TaskType taskType, Long taskDuration, DateTime startTimestamp) {
        super(id, priority, status, taskType, taskDuration);
        this.startTimestamp = startTimestamp;
    }

    @Override
    public DateTime expectedStartDate(){
        return startTimestamp;
    }
}
