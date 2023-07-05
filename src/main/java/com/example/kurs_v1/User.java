package com.example.kurs_v1;

public class User {
    static String login;
    static int password;
    static int userid;
    public User(){}

    public User(String login, int password) {
        User.login = login;
        User.password = password;
    }

    public static String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        User.login = login;
    }

    public static int getPassword() {
        return password;
    }

    public static void setPassword(int password) {
        User.password = password;
    }

    public static int getUserid(){
        return userid;
    }

    public static void setUserid(int userid) {
        User.userid = userid;
    }
}
