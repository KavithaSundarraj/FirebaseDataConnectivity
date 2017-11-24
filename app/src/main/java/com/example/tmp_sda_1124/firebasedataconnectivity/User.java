package com.example.tmp_sda_1124.firebasedataconnectivity;

/**
 * Created by tmp-sda-1124 on 11/24/17.
 */

public class User {
    String userId;
    String userName;
    String userpassword;


    public User()
    {

    }

    public User(String userId,String userName, String userpassword) {
        this.userId=userId;
        this.userName = userName;
        this.userpassword = userpassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public String getUserId() {
        return userId;
    }
}
