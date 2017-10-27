package calaerts.be.attendancesheet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import org.joda.time.LocalDate;

import calaerts.be.attendancesheet.Converters;

@Entity(primaryKeys = {"studentId", "hour", "day"}, foreignKeys = @ForeignKey(entity = StudentDb.class, parentColumns = "id", childColumns = "studentId"))
@TypeConverters(Converters.class)
public class MissedAttendance {
    @NonNull
    private final Integer studentId;
    @NonNull
    private final Hour hour;
    @NonNull
    private final LocalDate day;

    public MissedAttendance(@NonNull Integer studentId, @NonNull Hour hour, @NonNull LocalDate day) {
        this.studentId = studentId;
        this.hour = hour;
        this.day = day;
    }

    @NonNull
    public Integer getStudentId() {
        return studentId;
    }


    @NonNull
    public Hour getHour() {
        return hour;
    }

    @NonNull
    public LocalDate getDay() {
        return day;
    }
}
