package com.rakesh.scheduler.service;

import com.rakesh.scheduler.enums.*;
import com.rakesh.scheduler.pojo.Job;
import com.rakesh.scheduler.pojo.Task;
import com.rakesh.scheduler.pojo.TaskA;
import com.rakesh.scheduler.pojo.TaskB;
import org.joda.time.DateTime;

import java.util.Scanner;

/**
 * Created by rakesh on 12/06/20.
 */
public class CommandListener extends Listener {

    public CommandListener(JobService jobService) {
        super(jobService);
    }

    public void init() {
        System.out.println("Input expected in form of:");
        System.out.println("<Action> <Id> <Type> <Priority> <Duration in seconds> <T+startInMinutes> or <cron>");
        System.out.println("ADD,T1,A,MEDIUM,2,T+2");
        System.out.println("---or----");
        System.out.println("ADD,T2,B,MEDIUM,2,10 * *");
        System.out.println();
        System.out.println("For change:");
        System.out.println("<action> <id> <State>");
        System.out.println("CHANGE,T1,IN_ACTIVE");
        System.out.println();
        System.out.println("For query:");
        System.out.println("<action> <QUERY TYPE> <start> <end>");
        System.out.println("QUERY,HISTORY,T+2,T+4");
        System.out.println("---or----");
        System.out.println("<action> <QUERY TYPE> ");
        System.out.println("QUERY,ACTIVE");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            try {
                String[] split = input.split(",");
                if (split.length > 0) {
                    Action action = Action.valueOf(split[0]);

                    if (action == Action.ADD) {
                        String id = split[1];
                        TaskType type = TaskType.valueOf(split[2]);
                        Priority priority = Priority.valueOf(split[3]);
                        Long durationInSeconds = Long.valueOf(split[4]);
                        Task task;
                        if (type == TaskType.A) {
                            task = new TaskA(id, priority, State.ACTIVE, type, durationInSeconds,
                                    DateTimeSingleton.getReferenceTime()
                                            .plusMinutes(Integer.valueOf(split[5].split("\\+")[1])));
                        } else {
                            task = new TaskB(id, priority, State.ACTIVE, type, durationInSeconds, split[5]);
                        }
                        getJobService().add(task);

                    } else if (action == Action.CHANGE) {
                        String id = split[1];
                        State state = State.valueOf(split[2]);
                        getJobService().update(id, state);

                    } else if (action == Action.QUERY) {
                        QueryType queryType = QueryType.valueOf(split[1]);
                        if (queryType == QueryType.ACTIVE){
                           for(Task task : getJobService().getActiveTask()){
                               System.out.print(task.getId() + " " + task.getTaskType() + " " + task.getPriority() +
                                       " " + task.expectedStartDate() +" "+ task.getTaskDuration() + "\n");
                           }
                        }else if(queryType == QueryType.HISTORY){
                            DateTime startTime = DateTimeSingleton.getReferenceTime()
                                    .plusMinutes(Integer.valueOf(split[2].split("\\+")[1]));
                            DateTime endTime = DateTimeSingleton.getReferenceTime()
                                    .plusMinutes(Integer.valueOf(split[3].split("\\+")[1]));
                            for(Job job : getJobService ().get(startTime,endTime)){
                                System.out.print(job.getId() + " " + job.getStartDate() + " " + job.getEndDate() +
                                        " " + job.getResultType() +" "+ job.getTask() + "\n");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Issue in input " + input + " " + e.getMessage());
            }


        }
    }
}
