package com.bhanu.nutritracker.dto;

import java.util.List;

public class CustomFoodRequest {
    private Long userId;
    private String foodName;
    private List<NutrientDetail> nutrientsList;

    public CustomFoodRequest() {}

    public CustomFoodRequest(Long userId, String foodName, List<NutrientDetail> nutrientsList) {
        this.userId = userId;
        this.foodName = foodName;
        this.nutrientsList = nutrientsList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public List<NutrientDetail> getNutrientsList() {
        return nutrientsList;
    }

    public void setNutrientsList(List<NutrientDetail> nutrientsList) {
        this.nutrientsList = nutrientsList;
    }
}
