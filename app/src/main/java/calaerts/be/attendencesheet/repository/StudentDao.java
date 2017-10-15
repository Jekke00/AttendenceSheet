package calaerts.be.attendencesheet.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import calaerts.be.attendencesheet.model.Student;
@Dao
public interface StudentDao {
    @Query("select * from Student where klasId = :id")
    List<Student> loadStudentsByKlasId(int id);

    @Query("select * from Student where klasId = :id")
    LiveData<List<Student>> loadStudentsByKlasIdLiveData(int id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudent(Student student);

}
