package calaerts.be.attendencesheet.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import calaerts.be.attendencesheet.model.KlasDB;
import calaerts.be.attendencesheet.model.KlasWithStudentsAndMoments;

import static android.arch.persistence.room.OnConflictStrategy.ABORT;
@Dao()
public interface KlasDao {
    @Query("select * from klas")
    LiveData<List<KlasDB>> getAllKlas();

    @Query("select * from klas where id=:id")
    KlasDB getKlas(int id);

    @Insert(onConflict = ABORT)
    void insertKlas(KlasDB klas);

    @Update(onConflict = ABORT)
    void updateKlas(KlasDB klas);

    @Query("select * FROM Klas where id=:id")
    LiveData<KlasWithStudentsAndMoments> getKlasAggregate(int id);

}
