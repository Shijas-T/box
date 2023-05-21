package com.example.box;

public class AccountModel {

    private String userName;
    private String userUsn;
    private String userMobile;
    private String userEmail;

    public AccountModel() {
        // empty constructor
        // required for Firebase.
    }

    public AccountModel(String userName, String userUsn, String userMobile, String userEmail) {
        this.userName = userName;
        this.userUsn = userUsn;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUsn() {
        return userUsn;
    }

    public void setUserUsn(String userUsn) {
        this.userUsn = userUsn;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
