package com.bhanu.nutritracker.dto;

public class NutrientSummary {

    private Long nutrientId;
    private String nutrientName;
    private String nutrientUnitName;
    private Integer totalAmount;
    private Integer totalPercentDailyValue;

    public NutrientSummary(Long nutrientId, String nutrientName, String nutrientUnitName, Integer totalAmount, Integer totalPercentDailyValue) {
        this.nutrientId = nutrientId;
        this.nutrientName = nutrientName;
        this.nutrientUnitName = nutrientUnitName;
        this.totalAmount = totalAmount;
        this.totalPercentDailyValue = totalPercentDailyValue;
    }

    // Getters and setters

    public Long getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(Long nutrientId) {
        this.nutrientId = nutrientId;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    public String getNutrientUnitName() {
        return nutrientUnitName;
    }

    public void setNutrientUnitName(String nutrientUnitName) {
        this.nutrientUnitName = nutrientUnitName;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalPercentDailyValue() {
        return totalPercentDailyValue;
    }

    public void setTotalPercentDailyValue(Integer totalPercentDailyValue) {
        this.totalPercentDailyValue = totalPercentDailyValue;
    }
}
