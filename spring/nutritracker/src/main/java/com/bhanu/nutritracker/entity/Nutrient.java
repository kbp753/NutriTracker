package com.bhanu.nutritracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "nutrient")
public class Nutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrient_id")
    private Long id;

    @Column(name = "nutrient_nbr")
    private Long nutrientNumber;

    @Column(name = "nutrient_rank")
    private Long nutrientRank;

    @Column(name = "nutrient_name", columnDefinition = "TEXT")
    private String nutrientName;

    @Column(name = "nutrient_unit_name", columnDefinition = "TEXT")
    private String nutrientUnitName;

    // Constructors, getters, and setters
    // Constructor(s)...

    public Nutrient() {
    }

    // Getters and Setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNutrientNumber() {
        return nutrientNumber;
    }

    public void setNutrientNumber(Long nutrientNumber) {
        this.nutrientNumber = nutrientNumber;
    }

    public Long getNutrientRank() {
        return nutrientRank;
    }

    public void setNutrientRank(Long nutrientRank) {
        this.nutrientRank = nutrientRank;
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

    // toString() method...

    @Override
    public String toString() {
        return "Nutrient{" +
                "id=" + id +
                ", nutrientNumber=" + nutrientNumber +
                ", nutrientRank=" + nutrientRank +
                ", nutrientName='" + nutrientName + '\'' +
                ", nutrientUnitName='" + nutrientUnitName + '\'' +
                '}';
    }
}

