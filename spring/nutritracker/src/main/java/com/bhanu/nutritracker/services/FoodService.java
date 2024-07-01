package com.bhanu.nutritracker.services;

import com.bhanu.nutritracker.dto.CustomFoodRequest;
import com.bhanu.nutritracker.dto.FoodSearchResponse;
import com.bhanu.nutritracker.dto.NutrientDetail;
import com.bhanu.nutritracker.entity.*;
import com.bhanu.nutritracker.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FoodService {
    @Autowired
    private FoodRepository foodDao;
    @Autowired
    private FoodNutrientRepository foodNutrientRepo;

    @Autowired
    private NutrientRepository nutrientRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomFoodRepository customFoodRepository;

    @Autowired
    private CustomFoodNutrientRepository customFoodNutrientRepository;

    private final Logger logger=LoggerFactory.getLogger(this.getClass());;

    public List<FoodSearchResponse> getMatchedFoodNames(String foodName, Long userId) {
        String formattedSearchTerm = foodName.trim().replaceAll("\\s+", " & ");
        logger.debug("credentials ",userId);
        List<CustomFood> customFoodList= customFoodRepository.findByFoodNameContainingIgnoreCase(formattedSearchTerm, userId);
        List<Food> foodList=foodDao.findByFoodNameContainingIgnoreCase(formattedSearchTerm);

        List<FoodSearchResponse> searchList = new ArrayList<>();
        for(CustomFood customFood: customFoodList){
            FoodSearchResponse item = new FoodSearchResponse();
            item.setFoodName(customFood.getCustomFoodName());
            item.setFdcId(customFood.getCustomFoodId());
            item.setCreatedBy(customFood.getCreatedBy().getUserId());
            item.setIsCustom(true);
            searchList.add(item);
        }
        for(Food food: foodList){
            FoodSearchResponse item = new FoodSearchResponse();
            item.setFoodName(food.getFoodName());
            item.setFdcId( food.getFdcId().longValue());
            item.setIsCustom(false);
            searchList.add(item);
        }


        return searchList;
    }

    public List<FoodNutrient> getNutrientListByFoodId(Long fdcId){return foodNutrientRepo.getNutrientListByFoodId(fdcId);}

    public Food addFood(Food food) {
        return foodDao.save(food);
    }

//    public void getFoodNutritionListByFoodId(Long fdcId){
//        List<FoodNutrient> foodNutrientList=getNutrientListByFoodId(fdcId);
//        List<BigInteger> nutrientIdList=new ArrayList<BigInteger>();
//        for(FoodNutrient foodNutrient: foodNutrientList){
//            nutrientIdList.add(foodNutrient.getNutrientId());
//        }
//
//    }


    public List<Object[]> findNutrientDetailsByFdcId(Long fdcId){
        List<Object[]> a = foodNutrientRepo.findNutrientDetailsByFdcId(fdcId);
        return a;
    }

    public Food getFoodByFdcId(Long fdcId){
        return foodDao.getReferenceById(fdcId);
    }

    public List<NutrientDetail> getAllNutrients(String searchTerm) {
        return nutrientRepository.getAllNutrients(searchTerm);
    }

    public void saveCustomFood(CustomFoodRequest customFoodRequest) {
        CustomFood customFood=new CustomFood();
        LocalDate date=LocalDate.now();
        User user=userService.getUserById(customFoodRequest.getUserId());
        customFood.setCustomFoodName(customFoodRequest.getFoodName());
        customFood.setCreatedBy(user);
        customFood.setCreatedDate(date);
        customFoodRepository.save(customFood);
        for(NutrientDetail nutrientDetail: customFoodRequest.getNutrientsList()){
            Nutrient nutrient = nutrientRepository.getReferenceById(nutrientDetail.getId());
            CustomFoodNutrient customFoodNutrient=new CustomFoodNutrient();
            customFoodNutrient.setCustomFood(customFood);
            customFoodNutrient.setAmount(nutrientDetail.getAmount());
            customFoodNutrient.setNutrient((nutrient));
            customFoodNutrient.setPercentDailyValue(nutrientDetail.getDailyPercentage());
            customFoodNutrient.setCreatedDate(date);
            customFoodNutrient.setCreatedBy(user);
            customFoodNutrientRepository.save(customFoodNutrient);
        }


    }
}

