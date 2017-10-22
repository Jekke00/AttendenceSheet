package calaerts.be.attendancesheet.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Klas implements Serializable {
    private int id;
    private String name;
    private List<StudentDb> students = new ArrayList<>();
    private List<Moment> moments = new ArrayList<>();

    public Klas(KlasDB klasDB, List<StudentDb> students, List<Moment> moments) {
        this.id = klasDB.getId();
        this.name = klasDB.getName();
        this.students = students;
        this.moments = moments;
    }

    public String getName() {
        return name;
    }

    @NonNull
    public List<StudentDb> getStudents() {
        return students;
    }

    public int getId() {
        return id;
    }

    public List<Moment> getMoments() {
        return moments;
    }
}
