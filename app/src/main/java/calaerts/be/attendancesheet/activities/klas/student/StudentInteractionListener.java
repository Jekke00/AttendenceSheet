package calaerts.be.attendancesheet.activities.klas.student;

import calaerts.be.attendancesheet.model.StudentDb;

public interface StudentInteractionListener {
    void onStudentSelected(StudentDb student);
}
