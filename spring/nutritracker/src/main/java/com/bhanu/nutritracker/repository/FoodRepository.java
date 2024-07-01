package com.bhanu.nutritracker.repository;

import com.bhanu.nutritracker.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query(value = "SELECT * FROM food WHERE to_tsvector('english', food_name) @@ to_tsquery('english', :food_name) LIMIT 10", nativeQuery = true)
    List<Food> findByFoodNameContainingIgnoreCase(String food_name);
}
