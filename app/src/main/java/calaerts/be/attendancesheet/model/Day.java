package calaerts.be.attendancesheet.model;

import java.util.List;

public class Day {
    private final DayOfWeek dayOfWeek;
    private final List<Hour> hours;

    public Day(DayOfWeek dayOfWeek, List<Hour> hours) {
        this.dayOfWeek = dayOfWeek;
        this.hours = hours;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public List<Hour> getHours() {
        return hours;
    }
}
