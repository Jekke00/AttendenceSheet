package calaerts.be.attendancesheet.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class StudentWithMissedAttendances {
    @Embedded
    public StudentDb student;
    @Relation(parentColumn = "id", entityColumn = "studentId", entity = MissedAttendance.class)
    public List<MissedAttendance> missedAttendances;
    @ColumnInfo(name = "color")
    public int color;
}
