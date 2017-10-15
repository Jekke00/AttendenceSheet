package calaerts.be.attendencesheet.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import calaerts.be.attendencesheet.Converters;
import calaerts.be.attendencesheet.DayOfWeek;
import calaerts.be.attendencesheet.model.Moment;

@Dao
@TypeConverters(Converters.class)
public interface MomentDao {
    @Query("select * from Moment where klasId=:id and dayOfWeek=:dayOfWeek")
    LiveData<List<Moment>> getAllMomentsByKlasIdAndDayOfWeek(int id, DayOfWeek dayOfWeek);



    @Query("delete from Moment where klasId=:klasId and dayOfWeek=:dayOfWeek and hour=:hour ")
    void deleteMoment(int klasId, int dayOfWeek, int hour);

    @Insert
    void insert(Moment moment);
}
