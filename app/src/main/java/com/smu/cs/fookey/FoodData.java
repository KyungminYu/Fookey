package com.smu.cs.fookey;

/**
 * Created by LG on 2017-08-18.
 */

public class FoodData {
    private int id;
    private String food_name;
    private String path;
    private String date;

    public FoodData() {
    }
    public FoodData(String food_name, String path, String date) {
        this.food_name = food_name;
        this.path = path;
        this.date = date;
    }
    public FoodData(int id, String food_name, String path, String date) {
        this.id = id;
        this.food_name = food_name;
        this.path = path;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
