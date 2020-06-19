package com.rakesh.scheduler.service;

import org.joda.time.DateTime;

/**
 * Created by rakesh on 12/06/20.
 */
public class DateTimeSingleton {
    private static DateTimeSingleton singleton = null;
    private DateTime dateTime;

    private DateTimeSingleton() {
        dateTime = new DateTime();
    }

    private static DateTimeSingleton getInstance() {
        if (null == singleton) {
            singleton = new DateTimeSingleton();
        }
        return singleton;
    }

    public static DateTime getReferenceTime() {
        return getInstance().dateTime;
    }

}
