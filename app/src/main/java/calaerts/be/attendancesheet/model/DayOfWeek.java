package calaerts.be.attendancesheet.model;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public enum DayOfWeek {
    MONDAY(DateTimeConstants.MONDAY, "Maandag"),
    TUESDAY(DateTimeConstants.TUESDAY, "Dinsdag"),
    WEDNESDAY(DateTimeConstants.WEDNESDAY, "Woensdag"),
    THURSDAY(DateTimeConstants.THURSDAY, "Donderdag"),
    FRIDAY(DateTimeConstants.FRIDAY, "Vrijdag"),
    SATURDAY(DateTimeConstants.SATURDAY, "Zaterdag"),
    SUNDAY(DateTimeConstants.SUNDAY, "Zondag");

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

    public static DayOfWeek fromDate(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return DayOfWeek.getDayById(calendar.get(Calendar.DAY_OF_WEEK));
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
