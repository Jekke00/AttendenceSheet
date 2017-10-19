package calaerts.be.attendancesheet.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import calaerts.be.attendancesheet.model.KlasDB;
import calaerts.be.attendancesheet.model.KlasWithStudentsAndMoments;

import static android.arch.persistence.room.OnConflictStrategy.ABORT;
@Dao()
public interface KlasDao {
    @Query("select * from klas")
    LiveData<List<KlasDB>> getAllKlas();

    @Insert(onConflict = ABORT)
    void insertKlas(KlasDB klas);

    @Query("select * FROM Klas where id=:id")
    LiveData<KlasWithStudentsAndMoments> getKlasAggregate(int id);

}
