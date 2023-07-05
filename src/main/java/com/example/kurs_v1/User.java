package com.example.kurs_v1;

public class User {
    static String login;
    int password;
    static int userid;
    public User(){}

    public User(String login, int password) {
        User.login = login;
        this.password = password;
    }

    public static String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        User.login = login;
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
