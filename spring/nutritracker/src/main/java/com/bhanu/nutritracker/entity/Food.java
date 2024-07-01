package com.bhanu.nutritracker.entity;

import jakarta.persistence.*;

import java.util.Date;


@SqlResultSetMapping(
        name = "FoodResult",
        entities = {
                @EntityResult(
                        entityClass = Food.class,
                        fields = {
                                @FieldResult(name = "fdcId", column = "fdc_id"),
                                @FieldResult(name = "foodType", column = "food_type"),
                                @FieldResult(name = "foodName", column = "food_name"),
                                @FieldResult(name = "publicationDate", column = "publication_date"),
                                @FieldResult(name = "calories", column = "calories"),
                                @FieldResult(name = "carbohydrates", column = "carbohydrates"),
                                @FieldResult(name = "fats", column = "fats"),
                                @FieldResult(name = "minerals", column = "minerals"),
                                @FieldResult(name = "proteins", column = "proteins"),
                                @FieldResult(name = "vitamins", column = "vitamins")
                        }
                )
        }
)
@Entity
@Table(name = "food")
public class Food {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fdc_id")
    private Integer fdcId;

    @Column(name = "food_type")
    private String foodType;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "publication_date")
    private Date publicationDate;

    @Column(name = "calories")
    private Float calories;

    @Column(name = "carbohydrates")
    private Float carbohydrates;

    @Column(name = "fats")
    private Float fats;

    @Column(name = "minerals")
    private String minerals;

    @Column(name = "proteins")
    private Float proteins;

    @Column(name = "vitamins")
    private String vitamins;

    public Food() {
    }

    // Add constructors, getters, and setters

    public Integer getFdcId() {
        return fdcId;
    }

    public void setFdcId(Integer fdcId) {
        this.fdcId = fdcId;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public Float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Float getFats() {
        return fats;
    }

    public void setFats(Float fats) {
        this.fats = fats;
    }

    public String getMinerals() {
        return minerals;
    }

    public void setMinerals(String minerals) {
        this.minerals = minerals;
    }

    public Float getProteins() {
        return proteins;
    }

    public void setProteins(Float proteins) {
        this.proteins = proteins;
    }

    public String getVitamins() {
        return vitamins;
    }

    public void setVitamins(String vitamins) {
        this.vitamins = vitamins;
    }

    @Override
    public String toString() {
        return "Food{" +
                "fdcId=" + fdcId +
                ", foodType='" + foodType + '\'' +
                ", foodName='" + foodName + '\'' +
                ", publicationDate=" + publicationDate +
                ", calories=" + calories +
                ", carbohydrates=" + carbohydrates +
                ", fats=" + fats +
                ", minerals='" + minerals + '\'' +
                ", proteins=" + proteins +
                ", vitamins='" + vitamins + '\'' +
                '}';
    }
}
