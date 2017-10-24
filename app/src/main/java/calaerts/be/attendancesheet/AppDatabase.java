package calaerts.be.attendancesheet;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import calaerts.be.attendancesheet.model.KlasDB;
import calaerts.be.attendancesheet.model.MissedAttendance;
import calaerts.be.attendancesheet.model.Moment;
import calaerts.be.attendancesheet.model.StudentDb;
import calaerts.be.attendancesheet.repository.KlasDao;
import calaerts.be.attendancesheet.repository.MissedAttendanceDao;
import calaerts.be.attendancesheet.repository.MomentDao;
import calaerts.be.attendancesheet.repository.StudentDao;

@Database(entities = {StudentDb.class, KlasDB.class, Moment.class, MissedAttendance.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database")
                    .addMigrations(new Migration(1, 2) {
                        @Override
                        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                            supportSQLiteDatabase.execSQL("Alter table Klas add column color INTEGER NOT NULL DEFAULT -16776961;");
                        }
                    })
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract KlasDao klasDao();

    public abstract StudentDao studentDao();

    public abstract MomentDao momentDao();

    public abstract MissedAttendanceDao missedAttendanceDao();
}
