package calaerts.be.attendancesheet.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public enum DayOfWeek {
    MONDAY(Calendar.MONDAY, "Maandag"),
    TUESDAY(Calendar.TUESDAY, "Dinsdag"),
    WEDNESDAY(Calendar.WEDNESDAY, "Woensdag"),
    THURSDAY(Calendar.THURSDAY, "Donderdag"),
    FRIDAY(Calendar.FRIDAY, "Vrijdag"),
    SATURDAY(Calendar.SATURDAY, "Zaterdag"),
    SUNDAY(Calendar.SUNDAY, "Zondag");

    public final int id;
    public final String value;

    DayOfWeek(int id, String value){
        this.id = id;
        this.value = value;
    }

    public static DayOfWeek getDayById(int id){
        switch (id){
            case 0:
                return SUNDAY;
            case 1:
                return MONDAY;
            case 2:
                return TUESDAY;
            case 3:
                return WEDNESDAY;
            case 4:
                return THURSDAY;
            case 5:
                return FRIDAY;
            case 6:
                return SATURDAY;
            default:
                throw new IllegalArgumentException("unkown day code");
        }
    }

    public static DayOfWeek fromDate(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return DayOfWeek.getDayById(calendar.get(Calendar.DAY_OF_WEEK));
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
