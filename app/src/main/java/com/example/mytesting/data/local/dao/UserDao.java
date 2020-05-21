package com.example.mytesting.data.local.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.mytesting.data.model.User;

import java.util.List;

import io.reactivex.Observable;

@Dao
public abstract class UserDao implements BaseDao<User> {
    @Query("SELECT * FROM UserTbl")
    public abstract Observable<List<User>> getAllUsers();

    @Query("SELECT * FROM UserTbl WHERE login= :username")
    public abstract Observable<User> getUserByUsername(String username);
}
