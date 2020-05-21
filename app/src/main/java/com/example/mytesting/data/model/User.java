package com.example.mytesting.data.model;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "UserTable")
public class User {
    @SerializedName("id")
    private int mId;

    @SerializedName("login")
    private String mUsername;

    @SerializedName("name")
    private String mName;

    @SerializedName("company")
    private String mCompany;

    @SerializedName("blog")
    private String mBlog;

    @SerializedName("location")
    private String mLocation;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("avatar_url")
    private String mAvatarUrl;

    @SerializedName("type")
    private String mType;

    @SerializedName("followers_url")
    private String mFollowersUrl;
}
