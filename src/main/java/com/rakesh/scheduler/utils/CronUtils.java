package com.rakesh.scheduler.utils;

import com.rakesh.scheduler.pojo.TaskB;
import com.rakesh.scheduler.service.Scheduler;
import org.joda.time.DateTime;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by rakesh on 12/06/20.
 */
public final class CronUtils {

    public static DateTime getNextExpectedTime(String cronExpressionString) {
        try {
            CronExpression cronExpression = new CronExpression(cronExpressionString);
            cronExpression.setTimeZone(TimeZone.getTimeZone("IST"));
            Date _date = cronExpression.getNextValidTimeAfter(DateTime.now().plusMinutes(1).toDate());

            return null != _date ? new DateTime(_date) : null;
        } catch (ParseException ignored) {
            System.out.println("Invalid Cron : " + cronExpressionString);
        }
        return null;
    }
}
