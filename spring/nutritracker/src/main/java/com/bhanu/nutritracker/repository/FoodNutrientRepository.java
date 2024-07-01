package com.bhanu.nutritracker.repository;

import com.bhanu.nutritracker.entity.FoodNutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodNutrientRepository extends JpaRepository<FoodNutrient,Long> {

    @Query(value = "SELECT * FROM food_nutrient fn WHERE fdc_id=:fdcId", nativeQuery = true)
    List<FoodNutrient> getNutrientListByFoodId(Long fdcId);


    @Query(value = "SELECT n.nutrient_name, fn.amount, n.nutrient_unit_name, fn.percent_daily_value, fn.nutrient_id " +
            "FROM Food_Nutrient fn " +
            "JOIN nutrient n ON fn.nutrient_id = n.nutrient_id " +
            "WHERE fn.fdc_id = :fdcId " +
            "ORDER BY n.nutrient_rank;", nativeQuery = true)


//    @Query("SELECT fn.nutrient.nutrientName, fn.amount, fn.nutrient.nutrientUnitName, fn.percentDailyValue, fn.nutrientId " +
//            "FROM FoodNutrient fn " +
//            "WHERE fn.fdcId = :fdcId " +
//            "ORDER BY fn.nutrient.nutrientRank")
    List<Object[]> findNutrientDetailsByFdcId(@Param("fdcId") Long fdcId);


//    @Query(value = "SELECT n.nutrient_name, " +
//            "       SUM(fn.amount * fq.quantity) AS total_amount, " +
//            "       n.nutrient_unit_name, " +
//            "       SUM(fn.percent_daily_value) AS total_percent_daily_value " +
//            "FROM food_nutrient fn " +
//            "JOIN nutrient n ON n.nutrient_id = fn.nutrient_id " +
//            "WHERE fn.fdc_id = ANY(:fdcQtyList) " +
//            "GROUP BY n.nutrient_name, n.nutrient_unit_name " +
//            "ORDER BY n.nutrient_name",
//            nativeQuery = true)
//    List<Object[]> listNutreintDetailsForFdcList(@Param("fdcQtyList") List<Object[]> fdcQtyList);
}
