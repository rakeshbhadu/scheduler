package com.rakesh.scheduler;

import com.rakesh.scheduler.enums.State;
import com.rakesh.scheduler.service.*;
import com.rakesh.scheduler.pojo.TaskA;
import com.rakesh.scheduler.pojo.TaskB;
import org.joda.time.DateTime;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        JobService jobService = new JobServiceImpl();
        Scheduler scheduler = new Scheduler(jobService);
        Listener listener = new CommandListener(jobService);
        listener.init();
    }

}
