package com.bhanu.nutritracker.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NutrientDetail {

    private Long id;
    private String name;
    private String nutrientUnitName;
    private BigDecimal amount;

    private BigDecimal dailyPercentage;

    public NutrientDetail() {
    }

    public NutrientDetail(Long id, String name, String nutrientUnitName, Integer amount, Integer dailyPercentage) {
        this.id = id;
        this.name = name;
        this.nutrientUnitName = nutrientUnitName;
    }

    public NutrientDetail(Long id, String name, String nutrientUnitName, BigDecimal amount, BigDecimal dailyPercentage) {
        this.id = id;
        this.name = name;
        this.nutrientUnitName = nutrientUnitName;
        this.amount = amount;
        this.dailyPercentage = dailyPercentage;
    }

    public BigDecimal getDailyPercentage() {
        return dailyPercentage;
    }

    public void setDailyPercentage(BigDecimal dailyPercentage) {
        this.dailyPercentage = dailyPercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNutrientUnitName() {
        return nutrientUnitName;
    }

    public void setNutrientUnitName(String nutrientUnitName) {
        this.nutrientUnitName = nutrientUnitName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
