package com.example.mytesting.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import io.reactivex.Completable;

@Dao
public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable completableInsert(T entity);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(T entity);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(T[] entity);

    @Update
    void update(T entity);

    @Delete
    void delete(T entity);
}
