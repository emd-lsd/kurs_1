package com.example.kurs_v1;

import java.util.Date;

public class Orders {
    int id;
    String order_number;
    Date order_date;
    String furniture_name;
    int quantity;

    public Orders(int id, String order_number, Date order_date, String furniture_name, int quantity) {
        this.id = id;
        this.order_number = order_number;
        this.order_date = order_date;
        this.furniture_name = furniture_name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getFurniture_name() {
        return furniture_name;
    }

    public void setFurniture_name(String furniture_name) {
        this.furniture_name = furniture_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
