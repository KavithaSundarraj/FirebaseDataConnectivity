package com.example.tmp_sda_1124.firebasedataconnectivity;

/**
 * Created by tmp-sda-1124 on 11/24/17.
 */

public class User {
   private String userId;
    private String userName;
    private String userpassword;
   private long HighScore;
    private int Score;


    public User()
    {

    }

    public User(String userId,String userName, String userpassword) {
        this.userId=userId;
        this.userName = userName;
        this.userpassword = userpassword;
        this.HighScore=0;
        this.Score=0;
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

    public long getHighScore() {
        return HighScore;
    }

    public int getScore() {
        return Score;
    }
}
