package calaerts.be.attendencesheet.repository;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import calaerts.be.attendencesheet.model.Klas;
import calaerts.be.attendencesheet.model.KlasDB;
import calaerts.be.attendencesheet.model.KlasWithStudentsAndMoments;

public class KlasRepository {
    private final KlasDao klasDao;
    private final StudentDao studentDao;
    private LiveData<List<Klas>> allKlasses;
    private Function<List<KlasWithStudentsAndMoments>, List<Klas>> klasMapper =
            new Function<List<KlasWithStudentsAndMoments>, List<Klas>>() {
                @Override
                public List<Klas> apply(List<KlasWithStudentsAndMoments> klasWithStudentsAndMomentses) {
                    List<Klas> klases = new ArrayList<>();
                    for (KlasWithStudentsAndMoments klasDB : klasWithStudentsAndMomentses) {
                        klases.add(new Klas(klasDB.klas, klasDB.students, klasDB.moments));
                    }
                    return klases;
                }

            };

    public KlasRepository(KlasDao klasDao, StudentDao studentDao) {
        this.klasDao = klasDao;
        this.studentDao = studentDao;
    }

    public LiveData<List<KlasDB>> getAllKlassen() {
        return klasDao.getAllKlas();
    }

    public LiveData<Klas> getKlas(final int klasId){
        return Transformations.map(klasDao.getKlasAggregate(klasId), new Function<KlasWithStudentsAndMoments, Klas>() {
            @Override
            public Klas apply(KlasWithStudentsAndMoments aggregate) {
                return new Klas(aggregate.klas, aggregate.students, aggregate.moments);
            }
        });
    }
}
