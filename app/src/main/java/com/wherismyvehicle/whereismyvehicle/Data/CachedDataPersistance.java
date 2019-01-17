package com.wherismyvehicle.whereismyvehicle.Data;

import com.wherismyvehicle.whereismyvehicle.Models.Sighting;

import java.util.ArrayList;

public class CachedDataPersistance<T> implements DataPersinstance<T> {
    @Override
    public T Fetch(Object id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<T> FetchAll() {
        ArrayList<Sighting> sightings = new ArrayList<>();
        sightings.add(new Sighting(10, null, null, "Test1"));
        sightings.add(new Sighting(12, null, null, "Test2"));

        return (ArrayList<T>) sightings;
    }

    @Override
    public T Insert(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T Update(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void Delete(T object) {
        throw new UnsupportedOperationException();
    }
}
