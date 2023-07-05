package com.example.kurs_v1;

public class Furniture {
    int id;
    String type;
    String article;
    double price;
    String line;

    public Furniture(int id, String type, String article, double price, String line) {
        this.id = id;
        this.type = type;
        this.article = article;
        this.price = price;
        this.line = line;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
