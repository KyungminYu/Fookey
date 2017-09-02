package com.smu.cs.fookey.Network;

import com.smu.cs.fookey.SearchActivity;

import java.io.Serializable;

/**
 * Created by Chan on 2017-08-23.
 */
public class Description implements Serializable{
    private static final long serialVersionUID = 1L;

    private String food_name;
    private String food_category;
    private String calorie;
    private String safety;
    private Nutrient nutrient;

    public Description(String food_name, String food_category, String calorie, String safety, Nutrient nutrient) {
        this.food_name = food_name;
        this.food_category = food_category;
        this.calorie = calorie;
        this.safety = safety;
        this.nutrient = nutrient;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_category() {
        return food_category;
    }

    public void setFood_category(String food_category) {
        this.food_category = food_category;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getSafety() {
        return safety;
    }

    public void setSafety(String safety) {
        this.safety = safety;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }
}