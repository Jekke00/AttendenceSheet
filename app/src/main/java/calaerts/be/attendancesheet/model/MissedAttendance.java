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
    private Integer studentId;
    @NonNull
    private Hour hour;
    @NonNull
    private LocalDate day;

    public MissedAttendance(@NonNull Integer studentId, @NonNull Hour hour, @NonNull LocalDate day) {
        this.studentId = studentId;
        this.hour = hour;
        this.day = day;
    }

    public Integer getStudentId() {
        return studentId;
    }


    public Hour getHour() {
        return hour;
    }

    public LocalDate getDay() {
        return day;
    }
}
