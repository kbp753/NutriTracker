package com.bhanu.nutritracker.repository;

import com.bhanu.nutritracker.entity.CustomFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomFoodRepository extends JpaRepository<CustomFood, Long> {
    @Query(value = "SELECT * FROM custom_foods WHERE created_by= :userId and to_tsvector('english', custom_food_name) @@ to_tsquery('english', :custom_food_name) LIMIT 10", nativeQuery = true)
    List<CustomFood> findByFoodNameContainingIgnoreCase(String custom_food_name, Long userId);
}
