package calaerts.be.attendencesheet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import calaerts.be.attendencesheet.Converters;
import calaerts.be.attendencesheet.DayOfWeek;
import calaerts.be.attendencesheet.Hour;

@Entity(foreignKeys = @ForeignKey(entity = KlasDB.class, parentColumns = "id", childColumns = "klasId"), indices = {@Index(value = "klasId")})
@TypeConverters(Converters.class)
public class Moment {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private int klasId;
    private Hour hour;
    private DayOfWeek dayOfWeek;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
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

        if (!hour.equals(moment.hour)) return false;
        return dayOfWeek == moment.dayOfWeek;

    }

    @Override
    public int hashCode() {
        int result = hour.hashCode();
        result = 31 * result + dayOfWeek.hashCode();
        return result;
    }
}
