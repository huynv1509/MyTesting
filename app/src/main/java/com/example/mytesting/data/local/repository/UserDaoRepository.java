package com.example.mytesting.data.local.repository;

import com.example.mytesting.data.local.dao.UserDao;
import com.example.mytesting.data.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserDaoRepository {
    private UserDao mUserDao;

    @Inject
    public UserDaoRepository(UserDao userDao) {
        this.mUserDao = userDao;
    }

    public Observable<List<User>> getUserList() {
        return mUserDao.getAllUsers();
    }

    public Observable<User> getUserByUsername(String username) {
        return mUserDao.getUserByUsername(username);
    }
}
