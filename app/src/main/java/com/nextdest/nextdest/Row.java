package com.nextdest.activity;

/**
 * Created by hp on 10/31/2017.
 */

public class Row {

    private String name;
   private String date;
    private String Price;
    private String place;
    private String detal;
    private String time;
    private int id;

    public Row(String name, String date, String price, String place, String detal, String time, int id) {
        this.name = name;
        this.date = date;
        Price = price;
        this.place = place;
        this.detal = detal;
        this.time = time;
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDetal() {
        return detal;
    }

    public void setDetal(String detal) {
        this.detal = detal;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

