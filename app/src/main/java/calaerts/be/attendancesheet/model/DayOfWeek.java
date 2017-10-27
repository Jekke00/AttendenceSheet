package calaerts.be.attendancesheet.model;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public enum DayOfWeek {
    MONDAY(DateTimeConstants.MONDAY, "Monday"),
    TUESDAY(DateTimeConstants.TUESDAY, "Tuesday"),
    WEDNESDAY(DateTimeConstants.WEDNESDAY, "Wednesday"),
    THURSDAY(DateTimeConstants.THURSDAY, "Thursday"),
    FRIDAY(DateTimeConstants.FRIDAY, "Friday"),
    SATURDAY(DateTimeConstants.SATURDAY, "Saturday"),
    SUNDAY(DateTimeConstants.SUNDAY, "Sunday");

    public final int id;
    public final String value;

    DayOfWeek(int id, String value){
        this.id = id;
        this.value = value;
    }

    public static DayOfWeek getDayById(int id){
        switch (id){
            case DateTimeConstants.MONDAY:
                return MONDAY;
            case DateTimeConstants.TUESDAY:
                return TUESDAY;
            case DateTimeConstants.WEDNESDAY:
                return WEDNESDAY;
            case DateTimeConstants.THURSDAY:
                return THURSDAY;
            case DateTimeConstants.FRIDAY:
                return FRIDAY;
            case DateTimeConstants.SATURDAY:
                return SATURDAY;
            case DateTimeConstants.SUNDAY:
                return SUNDAY;
            default:
                throw new IllegalArgumentException("unkown day code");
        }
    }


    public static DayOfWeek of(LocalDate localDate) {
        return getDayById(localDate.getDayOfWeek());
    }

    public List<Hour> getAvailableHours() {
        if (WEDNESDAY.equals(this)) {
            return getHours(4);
        }
        return getHours(7);
    }

    private List<Hour> getHours(int maxHour) {
        List<Hour> hours = new ArrayList<>();
        for (int i = 1; i <= maxHour; i++) {
            hours.add(new Hour(i));
        }
        return hours;
    }

    @Override
    public String toString() {
        return value;
    }
}
