package com.bhanu.nutritracker.controllers;

import com.bhanu.nutritracker.dto.CustomFoodRequest;
import com.bhanu.nutritracker.dto.FoodSearchResponse;
import com.bhanu.nutritracker.dto.NutrientDetail;
import com.bhanu.nutritracker.entity.Food;
import com.bhanu.nutritracker.entity.FoodNutrient;
import com.bhanu.nutritracker.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/search")
    public ResponseEntity<List<FoodSearchResponse>> searchFoods(@RequestParam String foodName, @RequestParam Long userId) {
        List<FoodSearchResponse> matchedFoods = foodService.getMatchedFoodNames(foodName, userId);
        return ResponseEntity.ok(matchedFoods);
    }


    @GetMapping("/searchNutrientById")
    public ResponseEntity<List<FoodNutrient>> searchNutrientsById(@RequestParam Long fdcId) {
        List<FoodNutrient> matchedNutrients = foodService.getNutrientListByFoodId(fdcId);
        return ResponseEntity.ok(matchedNutrients);
    }



    @GetMapping("/searchFoodNutrientById")
    public ResponseEntity<List<Object[]>> findNutrientDetailsByFdcId(@RequestParam Long fdcId) {
        List<Object[]> matchedNutrients = foodService.findNutrientDetailsByFdcId(fdcId);
        return ResponseEntity.ok(matchedNutrients);
    }

    @GetMapping("/getAllNutrients")
    public ResponseEntity<List<NutrientDetail>> getAllNutrients(@RequestParam String searchTerm){
        List<NutrientDetail> nutrientDetailList=foodService.getAllNutrients(searchTerm);
        return ResponseEntity.ok(nutrientDetailList);
    }


    @PostMapping("/addCustomFood")
    public ResponseEntity<String> saveCustomFood(@RequestBody CustomFoodRequest customFood){
        foodService.saveCustomFood(customFood);
        return ResponseEntity.ok("Food saved");
    }


    @PostMapping
    public ResponseEntity<Food> addFood(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.addFood(food));
    }
}
