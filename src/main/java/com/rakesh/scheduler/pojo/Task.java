package com.rakesh.scheduler.pojo;

import com.rakesh.scheduler.enums.Priority;
import com.rakesh.scheduler.enums.State;
import com.rakesh.scheduler.enums.TaskType;
import lombok.*;
import org.joda.time.DateTime;


/**
 * Task class is an interface for TaskA and is directly managed by Game class.
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class Task {

    private String id;

    private Priority priority;

    private State state;

    private TaskType taskType;

    private Long taskDuration;

    private Task() {
    }

    public abstract DateTime expectedStartDate();
    
    public Boolean isRepetitiveTask(){
        return false;
    }
}