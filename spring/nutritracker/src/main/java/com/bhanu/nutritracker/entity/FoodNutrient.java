package com.bhanu.nutritracker.entity;

import com.bhanu.nutritracker.repository.NutrientRepository;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.math.BigDecimal;

@Entity
@Table(name = "food_nutrient")
public class FoodNutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodNutrientId", nullable = false)
    private BigInteger foodNutrientId;

    @Column(name = "fdc_id")
    private BigInteger fdcId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "data_points")
    private Float dataPoints;


    @Column(name = "derivation_id")
    private BigInteger derivationId;


    @Column(name = "min")
    private BigDecimal min;

    @Column(name = "max")
    private BigDecimal max;

    @Column(name = "median")
    private BigDecimal median;

    @Column(name = "loq")
    private BigDecimal loq;


    @Column(name = "footnote")
    private String footnote;

    @Column(name = "min_year_acquired")
    private Integer minYearAcquired;


    @Column(name = "percent_daily_value")
    private BigDecimal percentDailyValue;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nutrient_id", referencedColumnName = "nutrient_id")
    private Nutrient nutrient;

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }

    public BigInteger getFoodNutrientId() {
        return foodNutrientId;
    }

    public void setFoodNutrientId(BigInteger foodNutrientId) {
        this.foodNutrientId = foodNutrientId;
    }

    public BigInteger getFdcId() {
        return fdcId;
    }

    public void setFdcId(BigInteger fdcId) {
        this.fdcId = fdcId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Float getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(Float dataPoints) {
        this.dataPoints = dataPoints;
    }

    public BigInteger getDerivationId() {
        return derivationId;
    }

    public void setDerivationId(BigInteger derivationId) {
        this.derivationId = derivationId;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMedian() {
        return median;
    }

    public void setMedian(BigDecimal median) {
        this.median = median;
    }

    public BigDecimal getLoq() {
        return loq;
    }

    public void setLoq(BigDecimal loq) {
        this.loq = loq;
    }

    public String getFootnote() {
        return footnote;
    }

    public void setFootnote(String footnote) {
        this.footnote = footnote;
    }

    public Integer getMinYearAcquired() {
        return minYearAcquired;
    }

    public void setMinYearAcquired(Integer minYearAcquired) {
        this.minYearAcquired = minYearAcquired;
    }

    public BigDecimal getPercentDailyValue() {
        return percentDailyValue;
    }

    public void setPercentDailyValue(BigDecimal percentDailyValue) {
        this.percentDailyValue = percentDailyValue;
    }



}
