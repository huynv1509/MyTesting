package com.example.mytesting.data.remote.repository;

import com.example.mytesting.base.BaseRepository;
import com.example.mytesting.data.model.User;
import com.example.mytesting.data.remote.service.UserService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserRepository extends BaseRepository {
    private UserService mUserService;

    @Inject
    public UserRepository(UserService userService) {
        this.mUserService = userService;
    }

    public Observable<List<User>> getUsers() {
        return mUserService.getUsers();
    }

    public Observable<User> getUserDetail(String username) {
        return mUserService.getUserDetail(username);
    }

    public Observable<List<User>> getFollowers(String username) {
        return mUserService.getFollowers(username);
    }
}
