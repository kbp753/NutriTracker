package com.bhanu.nutritracker.services;

import com.bhanu.nutritracker.dto.NutrientSummary;
import com.bhanu.nutritracker.entity.DailyIntake;
import com.bhanu.nutritracker.entity.LogEntry;
import com.bhanu.nutritracker.entity.User;
import com.bhanu.nutritracker.repository.DailyIntakeRepository;
import com.bhanu.nutritracker.repository.FoodNutrientRepository;
import com.bhanu.nutritracker.repository.LogEntryRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DailyIntakeService {

    private static final Logger logger = LoggerFactory.getLogger(DailyIntakeService.class);

    @Autowired
    private DailyIntakeRepository dailyIntakeRepository;
    @Autowired
    private FoodNutrientRepository foodNutrientRepository;

    @Autowired
    private LogEntryRepository logEntryRepository;

//    public List<Object[]> getDailyIntakeByUserAndDate(User user, LocalDate date) {
//        List<Object[]> nutrientDetailsByDate = null;
//
//        List<Object[]> fdcQtyList = dailyIntakeRepository.listFoodsByUserAndDate(user, date);
////        List<Integer> fdcIds = new ArrayList<>();
////        List<Float> quantities = new ArrayList<>();
////
////        for (Object[] pair : fdcQtyList) {
////            fdcIds.add((Integer) pair[0]);
////            quantities.add((Float) pair[1]);
////        }
//        nutrientDetailsByDate = foodNutrientRepository.listNutreintDetailsForFdcList(fdcQtyList);
//        logger.debug("nutrient list __"+nutrientDetailsByDate);
//
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////
////        if (authentication != null && authentication.isAuthenticated()) {
////            Object principal = authentication.getPrincipal();
////
////            if (principal instanceof UserDetails) {
////                UserDetails userDetails = (UserDetails) principal;
////                logger.debug("Username: " + userDetails.getUsername());
////                logger.debug("Authorities: " + userDetails.getAuthorities());
////                logger.debug("user from input: "+user.getUserId());
////
////
////                List<Integer> fdcIdList = dailyIntakeRepository.listFoodsByUserAndDate(user, date);
////
////                nutrientDetailsByDate = foodNutrientRepository.listNutreintDetailsForFdcList(fdcIdList);
////
////            } else {
////                logger.debug("Principal: " + principal.toString());
////            }
////        } else {
////            logger.debug("No authenticated user found.");
////        }
//        return nutrientDetailsByDate;
//    }

    public List<NutrientSummary> getNutrientSummaryByUserIdAndLogDate(Long userId, LocalDate date) {
        List<NutrientSummary> nutrientSummaryByDate = logEntryRepository.getNutrientSummaryByUserIdAndLogDate(userId,date);

        return nutrientSummaryByDate;
    }

    public DailyIntake logDailyIntake(DailyIntake dailyIntake) {
        logger.debug(dailyIntake.toString());
        return dailyIntakeRepository.save(dailyIntake);
    }

    public LogEntry saveLogEntry(LogEntry logEntry){
        return logEntryRepository.save(logEntry);
    }

    public List<Object[]> listFoodsByUserAndDate(User user,LocalDate date){
        return dailyIntakeRepository.listFoodsByUserAndDate(user,date);
    }

    @Transactional
    public Integer updateLog(Long intakeId, Float quantity, Float updateProportion){
        logEntryRepository.updateLogEntry(dailyIntakeRepository.getReferenceById(intakeId), updateProportion );
        return dailyIntakeRepository.updateLog(intakeId, quantity);

    }

    @Transactional
    public void deleteLog(Long intakeId) {
        DailyIntake intake=dailyIntakeRepository.getReferenceById(intakeId);
        logEntryRepository.delete(intake);
        dailyIntakeRepository.delete(intake);
    }
}
