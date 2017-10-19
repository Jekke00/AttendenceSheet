package calaerts.be.attendancesheet;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import calaerts.be.attendancesheet.model.KlasDB;
import calaerts.be.attendancesheet.model.Moment;
import calaerts.be.attendancesheet.model.Student;
import calaerts.be.attendancesheet.repository.MomentDao;

@Database(entities = {Student.class, KlasDB.class, Moment.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract calaerts.be.attendancesheet.repository.KlasDao klasDao();

    public abstract calaerts.be.attendancesheet.repository.StudentDao studentDao();

    public abstract MomentDao momentDao();

}
