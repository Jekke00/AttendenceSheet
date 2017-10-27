package calaerts.be.attendancesheet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import calaerts.be.attendancesheet.Converters;

@Entity(foreignKeys = @ForeignKey(entity = KlasDB.class, parentColumns = "id", childColumns = "klasId"), indices = {@Index(value = "klasId")})
@TypeConverters(Converters.class)
public class Moment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int klasId;
    private Hour hour;
    private DayOfWeek dayOfWeek;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKlasId() {
        return klasId;
    }

    public void setKlasId(int klasId) {
        this.klasId = klasId;
    }

    public Hour getHour() {
        return hour;
    }

    public void setHour(Hour hour) {
        this.hour = hour;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moment moment = (Moment) o;

        return hour.equals(moment.hour) && dayOfWeek == moment.dayOfWeek;

    }

    @Override
    public int hashCode() {
        int result = hour.hashCode();
        result = 31 * result + dayOfWeek.hashCode();
        return result;
    }
}
