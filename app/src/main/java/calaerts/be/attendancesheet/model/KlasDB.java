package calaerts.be.attendancesheet.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "klas")
public class KlasDB implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    private
    @ColorInt
    int color;

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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KlasDB klasDB = (KlasDB) o;
        return id == klasDB.id &&
                Objects.equals(name, klasDB.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
