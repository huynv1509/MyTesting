package com.example.mytesting.utils;

import android.text.TextUtils;

import com.example.mytesting.constants.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;

public class CommonUtils {
    public static boolean validateEmail(@NotNull String email) {
        if (!TextUtils.isEmpty(email.trim())) {
            Matcher matcher = Constants.EMAIL_PATTERN.matcher(email);
            return matcher.matches();
        } else {
            return false;
        }
    }
}
