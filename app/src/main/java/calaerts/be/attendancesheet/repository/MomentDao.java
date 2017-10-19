package calaerts.be.attendancesheet.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import calaerts.be.attendancesheet.Converters;
import calaerts.be.attendancesheet.model.DayOfWeek;
import calaerts.be.attendancesheet.model.Hour;
import calaerts.be.attendancesheet.model.Moment;

@Dao
@TypeConverters(Converters.class)
public interface MomentDao {
    @Query("select * from Moment where klasId=:id and dayOfWeek=:dayOfWeek")
    LiveData<List<Moment>> getAllMomentsByKlasIdAndDayOfWeek(int id, DayOfWeek dayOfWeek);

    @Query("delete from Moment where klasId=:klasId and dayOfWeek=:dayOfWeek and hour=:hour ")
    void deleteMoment(int klasId, int dayOfWeek, int hour);

    @Insert
    void insert(Moment moment);

    @Query("select distinct(moment.hour) from Moment moment where moment.dayOfWeek=:dayOfWeek")
    LiveData<List<Hour>> getHoursAtDay(DayOfWeek dayOfWeek);
}
