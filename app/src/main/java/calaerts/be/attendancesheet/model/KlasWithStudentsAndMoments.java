package calaerts.be.attendancesheet.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class KlasWithStudentsAndMoments {
    @Embedded
    public KlasDB klas;

    @Relation(parentColumn = "id", entityColumn = "klasId", entity = StudentDb.class)
    public List<StudentDb> students;

    @Relation(parentColumn = "id", entityColumn = "klasId", entity = Moment.class)
    public List<Moment> moments;


}
