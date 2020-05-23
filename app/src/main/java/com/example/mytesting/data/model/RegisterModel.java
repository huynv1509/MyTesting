package com.example.mytesting.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mytesting.constants.RegisterStatus;
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

    private boolean isNameValid() {
        return StringUtils.isNotEmpty(mName.trim());
    }

    private boolean isEmailValid() {
        return StringUtils.isNotEmpty(mEmail.trim());
    }

    private boolean isPasswordValid() {
        return StringUtils.isNotEmpty(mPassword.trim());
    }

    private boolean isConfirmEmailValid() {
        if (StringUtils.isNotEmpty(mEmail.trim()) && StringUtils.isNotEmpty(mConfirmEmail.trim()))
            return CommonUtils.validateEmail(mEmail) && mEmail.equals(mConfirmEmail);
        return false;
    }

    private boolean isConfirmPasswordValid() {
        if (StringUtils.isNotEmpty(mPassword.trim()) && StringUtils.isNotEmpty(mConfirmPassword.trim()))
            return mPassword.equals(mConfirmPassword);
        return false;
    }

    public RegisterStatus registerStatus() {
        if (!isNameValid()) {
            return RegisterStatus.NAME_ERROR;
        } else if (!isEmailValid()) {
            return RegisterStatus.EMAIL_ERROR;
        } else if (!isConfirmEmailValid()) {
            return RegisterStatus.CONFIRM_EMAIL_ERROR;
        } else if (!isPasswordValid()) {
            return RegisterStatus.PASSWORD_ERROR;
        } else if (!isConfirmPasswordValid()) {
            return RegisterStatus.CONFIRM_PASSWORD_ERROR;
        }
        return RegisterStatus.SUCCESS;
    }
}
