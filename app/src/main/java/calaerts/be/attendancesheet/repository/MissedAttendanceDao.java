package calaerts.be.attendancesheet.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.TypeConverters;

import calaerts.be.attendancesheet.Converters;
import calaerts.be.attendancesheet.model.MissedAttendance;

@Dao
@TypeConverters(Converters.class)
public interface MissedAttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAttendance(MissedAttendance missedAttendance);

    @Delete
    void deleteMissedAttendance(MissedAttendance missedAttendance);
}
