package calaerts.be.attendencesheet;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import calaerts.be.attendencesheet.model.KlasDB;
import calaerts.be.attendencesheet.model.Moment;
import calaerts.be.attendencesheet.model.Student;
import calaerts.be.attendencesheet.repository.MomentDao;

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

    public abstract calaerts.be.attendencesheet.repository.KlasDao klasDao();

    public abstract calaerts.be.attendencesheet.repository.StudentDao studentDao();

    public abstract MomentDao momentDao();

}
