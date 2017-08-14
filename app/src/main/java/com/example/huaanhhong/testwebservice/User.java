package com.example.huaanhhong.testwebservice;

/**
 * Created by huaanhhong on 11/08/2017.
 */

public class User {

    private String Email, Password, ConfirmPassword;

    public User(String mEmail, String mPassword, String mConfirmPassword) {

        this.Email = mEmail;
        this.Password = mPassword;
        this.ConfirmPassword = mConfirmPassword;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String mEmail) {
        this.Email = mEmail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String mPassword) {
        this.Password = mPassword;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String mConfirmPassword) {
        this.ConfirmPassword = mConfirmPassword;
    }
}

