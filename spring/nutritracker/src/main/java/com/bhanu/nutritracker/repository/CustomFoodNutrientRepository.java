package com.bhanu.nutritracker.repository;

import com.bhanu.nutritracker.entity.CustomFoodNutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomFoodNutrientRepository extends JpaRepository<CustomFoodNutrient, Long> {
}
