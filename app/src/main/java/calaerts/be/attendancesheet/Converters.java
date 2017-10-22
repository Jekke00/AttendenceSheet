package calaerts.be.attendancesheet;

import android.arch.persistence.room.TypeConverter;

import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.LocalDate;

import calaerts.be.attendancesheet.model.DayOfWeek;
import calaerts.be.attendancesheet.model.Hour;

public class Converters {
    @TypeConverter
    public static DayOfWeek dayOfWeek(int dayOfWeek) {
        return DayOfWeek.getDayById(dayOfWeek);
    }

    @TypeConverter
    public static int toValue(DayOfWeek dayOfWeek) {
        return dayOfWeek.id;
    }

    @TypeConverter
    public static Hour hour(int hour) {
        final Hour hour1 = new Hour(hour);
        hour1.setSelected(true);
        return hour1;
    }

    @TypeConverter
    public static int hourToDb(Hour hour) {
        return hour.getHour();
    }

    @TypeConverter
    public static long localDateToDb(LocalDate localDate) {
        return localDate.toDateTimeAtStartOfDay(DateTimeZone.UTC).toInstant().getMillis();
    }

    @TypeConverter
    public static LocalDate localDateFromDb(long date) {
        return new LocalDate(new Instant(date), DateTimeZone.UTC);
    }
}
