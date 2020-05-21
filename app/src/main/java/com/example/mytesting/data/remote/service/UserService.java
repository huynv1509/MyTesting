package com.example.mytesting.data.remote.service;

import com.example.mytesting.data.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("users")
    Observable<List<User>> getUsers();

    @GET("users/{username}")
    Observable<User> getUserDetail(@Path("username") String username);

    @GET("users/{username}/followers")
    Observable<List<User>> getFollowers(@Path("username") String username);
}
