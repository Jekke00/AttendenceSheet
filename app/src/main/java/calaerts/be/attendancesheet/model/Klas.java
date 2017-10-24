package calaerts.be.attendancesheet.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Klas implements Serializable {
    private KlasDB klasDB;
    private List<StudentDb> students = new ArrayList<>();
    private List<Moment> moments = new ArrayList<>();

    public Klas(KlasDB klasDB, List<StudentDb> students, List<Moment> moments) {
        this.klasDB = klasDB;
        this.students = students;
        this.moments = moments;
    }

    public String getName() {
        return klasDB.getName();
    }

    @NonNull
    public List<StudentDb> getStudents() {
        return students;
    }

    public int getId() {
        return klasDB.getId();
    }

    public List<Moment> getMoments() {
        return moments;
    }

    public KlasDB getKlasDb() {
        return klasDB;
    }

    public int getColor() {
        return klasDB.getColor();
    }
}
