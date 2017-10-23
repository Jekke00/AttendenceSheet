package calaerts.be.attendancesheet.model;

import android.support.annotation.NonNull;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student> {
    private final StudentDb student;
    private final List<MissedAttendance> missedAttendances;

    public Student(StudentDb student, List<MissedAttendance> missedAttendances) {
        this.student = student;
        this.missedAttendances = missedAttendances;
    }

    public String getName() {
        return student.getName();
    }

    public int getId() {
        return student.getId();
    }

    public List<MissedAttendance> getMissedAttendances() {
        return new ArrayList<>(missedAttendances);
    }

    public MissedAttendance getMissedAttendanceAtDate(LocalDate date, Hour hour) {
        for (MissedAttendance missedAttendance : missedAttendances) {
            if (missedAttendance.getDay().equals(date) && missedAttendance.getHour().equals(hour))
                return missedAttendance;
        }
        return null;
    }

    public boolean hasMissedAttendanceAtDate(LocalDate date, Hour hour) {
        return getMissedAttendanceAtDate(date, hour) != null;
    }


    @Override
    public int compareTo(@NonNull Student student) {
        return getName().compareTo(student.getName());
    }
}
