package com.bhanu.nutritracker.dto;

public class AdjustedNutrient {

    private Long nutrientId;
    private Double amount;
    private Double dailyPercentage;

    public AdjustedNutrient() {
    }

    public AdjustedNutrient(Long nutrientId, Double amount, Double dailyPercentage) {
        this.nutrientId = nutrientId;
        this.amount = amount;
        this.dailyPercentage = dailyPercentage;
    }

    public Long getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(Long nutrientId) {
        this.nutrientId = nutrientId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDailyPercentage() {
        return dailyPercentage;
    }

    public void setDailyPercentage(Double dailyPercentage) {
        this.dailyPercentage = dailyPercentage;
    }
}
