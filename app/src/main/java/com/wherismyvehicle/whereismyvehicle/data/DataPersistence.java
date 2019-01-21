package com.wherismyvehicle.whereismyvehicle.data;

public interface DataPersistence <T> {
    DataPersistenceAction<T> Fetch(Class serializationClass, Object id);
    DataPersistenceAction<T[]> FetchAll(Class serializationClass);
    DataPersistenceAction<T> Insert(Class serializationClass, T object);
    DataPersistenceAction<T[]> Insert(Class serializationClass, T[] object);
    DataPersistenceAction<T> Update(Class serializationClass, T object);
    DataPersistenceAction Delete(Class serializationClass, Object id);
    DataPersistenceAction DeleteAll(Class serializationClass);
}
