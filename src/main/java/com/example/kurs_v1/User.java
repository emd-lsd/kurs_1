package com.example.kurs_v1;

public class User {
    String login;
    int password;
    static int userid;
    public User(){}

    public User(String login, int password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public static int getUserid(){
        return userid;
    }

    public static void setUserid(int userid) {
        User.userid = userid;
    }
}
