package com.example.kurs_v1;

public class Stores {
    int id;
    String address;
    int fax_number;
    int user_id;

    public Stores(int id, String address, int fax_number, int user_id) {
        this.id = id;
        this.address = address;
        this.fax_number = fax_number;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFax_number() {
        return fax_number;
    }

    public void setFax_number(int fax_number) {
        this.fax_number = fax_number;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
