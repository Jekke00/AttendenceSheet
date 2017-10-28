package calaerts.be.attendancesheet.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import calaerts.be.attendancesheet.model.KlasDB;
import calaerts.be.attendancesheet.model.KlasWithStudentsAndMoments;

@Dao
public interface KlasDao {
    @Query("select * from klas")
    LiveData<List<KlasDB>> getAllKlas();

    @Insert
    void insertKlas(KlasDB klas);

    @Update
    void updateKlas(KlasDB klas);

    @Query("select * FROM Klas where id=:id")
    LiveData<KlasWithStudentsAndMoments> getKlasAggregate(int id);

}
