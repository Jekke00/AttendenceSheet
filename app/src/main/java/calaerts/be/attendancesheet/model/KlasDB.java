package calaerts.be.attendancesheet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
@Entity(tableName = "klas")
public class KlasDB {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;

    @NonNull
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
}
