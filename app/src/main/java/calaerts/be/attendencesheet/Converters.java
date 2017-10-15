package calaerts.be.attendencesheet;

import android.arch.persistence.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static DayOfWeek dayOfWeek(int dayOfWeek){
        return DayOfWeek.getDayById(dayOfWeek);
    }
    @TypeConverter
    public static int toValue(DayOfWeek dayOfWeek){
        return dayOfWeek.id;
    }
    @TypeConverter
    public static Hour hour(int hour){
        final Hour hour1 = new Hour(hour);
        hour1.setSelected(true);
        return hour1;
    }
    @TypeConverter
    public static int hourToDb(Hour hour){
        return hour.getHour();
    }
}
