package calaerts.be.attendancesheet.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
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
}
