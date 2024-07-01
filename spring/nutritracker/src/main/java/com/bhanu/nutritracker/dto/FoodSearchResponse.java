package com.bhanu.nutritracker.dto;

public class FoodSearchResponse {
    private Long fdcId;
    private String foodName;

    private Long createdBy;

    private Boolean isCustom;

    public Boolean getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(Boolean custom) {
        isCustom = custom;
    }

    public Long getFdcId() {
        return fdcId;
    }

    public void setFdcId(Long fdcId) {
        this.fdcId = fdcId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
