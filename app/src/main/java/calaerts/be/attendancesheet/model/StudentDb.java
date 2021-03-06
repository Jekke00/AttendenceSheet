package calaerts.be.attendancesheet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = KlasDB.class, parentColumns = "id", childColumns = "klasId"), indices = {@Index(value = "klasId")})
public class StudentDb {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    private int klasId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKlasId() {
        return klasId;
    }

    public void setKlasId(int klasId) {
        this.klasId = klasId;
    }
}
