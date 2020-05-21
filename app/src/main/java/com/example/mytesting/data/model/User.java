package com.example.mytesting.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "UserTbl")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "login")
    @SerializedName("login")
    private String mUsername;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String mName;

    @ColumnInfo(name = "company")
    @SerializedName("company")
    private String mCompany;

    @ColumnInfo(name = "blog")
    @SerializedName("blog")
    private String mBlog;

    @ColumnInfo(name = "location")
    @SerializedName("location")
    private String mLocation;

    @ColumnInfo(name = "email")
    @SerializedName("email")
    private String mEmail;

    @ColumnInfo(name = "url")
    @SerializedName("url")
    private String mUrl;

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    private String mAvatarUrl;

    @ColumnInfo(name = "followers_url")
    @SerializedName("followers_url")
    private String mFollowersUrl;

    public User(String username, String name, String company, String blog, String location, String email, String url, String avatarUrl, String followersUrl) {
        this.mUsername = username;
        this.mName = name;
        this.mCompany = company;
        this.mBlog = blog;
        this.mLocation = location;
        this.mEmail = email;
        this.mUrl = url;
        this.mAvatarUrl = avatarUrl;
        this.mFollowersUrl = followersUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        this.mCompany = company;
    }

    public String getBlog() {
        return mBlog;
    }

    public void setBlog(String blog) {
        this.mBlog = blog;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.mAvatarUrl = avatarUrl;
    }

    public String getFollowersUrl() {
        return mFollowersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.mFollowersUrl = followersUrl;
    }
}
