package com.example.mytesting.data.local.repository;

import com.example.mytesting.data.local.dao.RegisterDao;
import com.example.mytesting.data.model.RegisterModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class RegisterUserRepository {
    private RegisterDao mRegisterDao;

    @Inject
    public RegisterUserRepository(RegisterDao registerDao) {
        this.mRegisterDao = registerDao;
    }

    public Completable insertRegisterUser(RegisterModel registerModel) {
        return mRegisterDao.completableInsert(registerModel);
    }

    public Observable<List<RegisterModel>> getRegisterUsers() {
        return mRegisterDao.getRegisterUsers();
    }
}
