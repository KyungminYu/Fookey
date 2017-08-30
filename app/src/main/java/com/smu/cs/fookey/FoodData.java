package com.smu.cs.fookey;

/**
 * Created by LG on 2017-08-18.
 */

public class FoodData {
    private int id;
    private String category;
    private String date;

    public FoodData() {
    }

    public FoodData(String category, String date) {
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

}
