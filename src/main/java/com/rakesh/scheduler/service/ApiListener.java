package com.rakesh.scheduler.service;

import java.util.Scanner;

/**
 * Created by rakesh on 12/06/20.
 */
public class ApiListener extends Listener {

    ApiListener(JobService jobService) {
        super(jobService);
    }

    @Override
    public void init() {

    }
}
