package com.example.mytesting.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mytesting.utils.CommonUtils;

import org.apache.commons.lang3.StringUtils;

@Entity(tableName = "registerTbl")
public class RegisterModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "email")
    private String mEmail;

    @ColumnInfo(name = "confirmEmail")
    private String mConfirmEmail;

    @ColumnInfo(name = "password")
    private String mPassword;

    @ColumnInfo(name = "confirmPassword")
    private String mConfirmPassword;

    public RegisterModel(String name, String email, String confirmEmail, String password, String confirmPassword) {
        this.mName = name;
        this.mEmail = email;
        this.mConfirmEmail = confirmEmail;
        this.mPassword = password;
        this.mConfirmPassword = confirmPassword;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getConfirmEmail() {
        return mConfirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.mConfirmEmail = confirmEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getConfirmPassword() {
        return mConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.mConfirmPassword = confirmPassword;
    }

    public boolean isNameValid() {
        return StringUtils.isNotEmpty(mName.trim());
    }

    public boolean isEmailValid() {
        return StringUtils.isNotEmpty(mEmail.trim());
    }

    public boolean isPasswordValid() {
        return StringUtils.isNotEmpty(mPassword.trim());
    }

    public boolean isConfirmEmailValid() {
        if (StringUtils.isNotEmpty(mEmail.trim()) && StringUtils.isNotEmpty(mConfirmEmail.trim()))
            return CommonUtils.validateEmail(mEmail) && mEmail.equals(mConfirmEmail);
        return false;
    }

    public boolean isConfirmPasswordValid() {
        if (StringUtils.isNotEmpty(mPassword.trim()) && StringUtils.isNotEmpty(mConfirmPassword.trim()))
            return mPassword.equals(mConfirmPassword);
        return false;
    }
}
