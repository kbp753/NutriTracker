package com.bhanu.nutritracker.dto;

import java.time.LocalDate;
import java.util.List;

public class LogEntryRequest {
    private Long userId;
    private Long fdcId;
    private LocalDate date;
    private Float quantity;
    private List<AdjustedNutrient> adjustedNutrients; // This represents the array of arrays from adjustedNutrients

    private Long intakeId;

    public Long getIntakeId() {
        return intakeId;
    }

    public void setIntakeId(Long intakeId) {
        this.intakeId = intakeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFdcId() {
        return fdcId;
    }

    public void setFdcId(Long fdcId) {
        this.fdcId = fdcId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public List<AdjustedNutrient> getAdjustedNutrients() {
        return adjustedNutrients;
    }

    public void setAdjustedNutrients(List<AdjustedNutrient> adjustedNutrients) {
        this.adjustedNutrients = adjustedNutrients;
    }

}
