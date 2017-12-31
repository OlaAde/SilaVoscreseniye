package com.example.adeogo.silavoscresenye.model;

/**
 * Created by ademi on 12/30/17.
 */

public class CalendarEvent {
    public String Summary;
    public String StartTime;
    public String EndTime;

    public CalendarEvent() {
    }

    public CalendarEvent(String summary, String startTime, String endTime) {
        Summary = summary;
        StartTime = startTime;
        EndTime = endTime;
    }

    public String getName() {
        return Summary;
    }

    public void setName(String summary) {
        Summary = summary;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }
}
