package com.bhanu.nutritracker.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "custom_food_nutrient")
public class CustomFoodNutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customFoodNutrientId;

    @ManyToOne
    @JoinColumn(name = "custom_food_id", nullable = false)
    private CustomFood customFood;

    @ManyToOne
    @JoinColumn(name="nutrient_id",nullable = false)
    private Nutrient nutrient;

    @Column(name="amount",nullable = false)
    private BigDecimal amount;

    @Column(name = "percent_daily_value")
    private BigDecimal percentDailyValue;

    @JoinColumn(name = "created_by", referencedColumnName = "user_id", nullable= false)
    @ManyToOne
    private User createdBy;

    @Column(name="created_date")
    private LocalDate createdDate;

    public Long getCustomFoodNutrientId() {
        return customFoodNutrientId;
    }

    public void setCustomFoodNutrientId(Long customFoodNutrientId) {
        this.customFoodNutrientId = customFoodNutrientId;
    }

    public CustomFood getCustomFood() {
        return customFood;
    }

    public void setCustomFood(CustomFood customFood) {
        this.customFood = customFood;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPercentDailyValue() {
        return percentDailyValue;
    }

    public void setPercentDailyValue(BigDecimal percentDailyValue) {
        this.percentDailyValue = percentDailyValue;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
