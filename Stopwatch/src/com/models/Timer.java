package com.models;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormat;

public class Timer {
    private DateTime startTime;
    private DateTime endTime;
    private boolean running;

    public String getStartTime() {
        System.out.println(startTime);
        return formatTime(startTime);
    }

    public String getEndTime() {
        return formatTime(curEndTime());
    }

    public String currentTime() {
        return formatTime(new DateTime());
    }

    private String formatTime(DateTime time) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("hh:mm a");
        return fmt.print(time);
    }

    public void stopTimer() {
        this.endTime = new DateTime();
        this.running = false;
    }

    public void startTimer() {
        this.startTime = new DateTime();
        this.running = true;
    }

    private DateTime curEndTime() {
        @SuppressWarnings("unused")
		DateTime curEndTime;
        if (endTime == null) {
            return new DateTime();
        } else {
            return endTime;
        }
    }

    public int calculateTimeMinutes() {
        formatTime(endTime);
        return Minutes.minutesBetween(startTime,curEndTime()).getMinutes();
    }

    public int calculateTimeSeconds() {
        return Seconds.secondsBetween(startTime,curEndTime()).getSeconds();
    }

    public boolean isRunning() {
        return running;
    }
}
