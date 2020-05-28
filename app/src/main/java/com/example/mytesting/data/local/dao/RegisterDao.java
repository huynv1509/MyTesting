package com.example.mytesting.data.local.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.mytesting.data.model.RegisterModel;

import java.util.List;

import io.reactivex.Observable;

@Dao
public abstract class RegisterDao implements BaseDao<RegisterModel> {
    @Query("SELECT * FROM registerTbl")
    public abstract Observable<List<RegisterModel>> getRegisterUsers();
}
