package com.example.mytesting.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mytesting.data.local.dao.RegisterDao;
import com.example.mytesting.data.local.dao.UserDao;
import com.example.mytesting.data.model.RegisterModel;
import com.example.mytesting.data.model.User;

@Database(entities = {User.class, RegisterModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract RegisterDao registerDao();
}
