package calaerts.be.attendancesheet.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import java.util.List;

import calaerts.be.attendancesheet.model.Klas;
import calaerts.be.attendancesheet.model.KlasDB;

public class KlasRepository {
    private final KlasDao klasDao;

    public KlasRepository(KlasDao klasDao) {
        this.klasDao = klasDao;
    }

    public LiveData<List<KlasDB>> getAllKlassen() {
        return klasDao.getAllKlas();
    }

    public LiveData<Klas> getKlas(final int klasId){
        return Transformations.map(klasDao.getKlasAggregate(klasId), aggregate -> new Klas(aggregate.klas, aggregate.students, aggregate.moments));
    }
}
