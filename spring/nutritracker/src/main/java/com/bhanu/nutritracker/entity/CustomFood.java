package com.bhanu.nutritracker.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "custom_foods")
public class CustomFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custom_food_id")
    private Long customFoodId;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "user_id", nullable = false)
    private User createdBy;

    @Column(name = "custom_food_name", nullable = false)
    private String customFoodName;

    @Column(name="created_date")
    private LocalDate createdDate;

    public Long getCustomFoodId() {
        return customFoodId;
    }

    public void setCustomFoodId(Long customFoodId) {
        this.customFoodId = customFoodId;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User user) {
        this.createdBy = user;
    }

    public String getCustomFoodName() {
        return customFoodName;
    }

    public void setCustomFoodName(String customFoodName) {
        this.customFoodName = customFoodName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}

