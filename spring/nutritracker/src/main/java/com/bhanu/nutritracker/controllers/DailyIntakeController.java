package com.bhanu.nutritracker.controllers;

import com.bhanu.nutritracker.dto.AdjustedNutrient;
import com.bhanu.nutritracker.dto.LogEntryRequest;
import com.bhanu.nutritracker.dto.NutrientSummary;
import com.bhanu.nutritracker.entity.DailyIntake;
import com.bhanu.nutritracker.entity.Food;
import com.bhanu.nutritracker.entity.LogEntry;
import com.bhanu.nutritracker.entity.User;
import com.bhanu.nutritracker.repository.NutrientRepository;
import com.bhanu.nutritracker.services.DailyIntakeService;
import com.bhanu.nutritracker.services.FoodService;
import com.bhanu.nutritracker.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dailyIntake")
public class DailyIntakeController {
    @Autowired
    private DailyIntakeService dailyIntakeService;
    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private NutrientRepository nutrientRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/log")
    public ResponseEntity<DailyIntake> logDailyIntake(@RequestBody DailyIntake dailyIntake) {
        return ResponseEntity.ok(dailyIntakeService.logDailyIntake(dailyIntake));
    }

    @GetMapping("/getLog")
    public ResponseEntity<List<NutrientSummary>> getDailyIntake(@RequestParam Long userId, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(dailyIntakeService.getNutrientSummaryByUserIdAndLogDate(userId, localDate));
    }


    @PostMapping("/create")
    public ResponseEntity<String> createLogEntry(@RequestBody LogEntryRequest logEntryRequest) {
        User user = userService.getUserById(logEntryRequest.getUserId());
        Food food= foodService.getFoodByFdcId(logEntryRequest.getFdcId());
        List<AdjustedNutrient> adjustedNutrients = logEntryRequest.getAdjustedNutrients();
        DailyIntake dailyIntake = new DailyIntake(user,food,logEntryRequest.getDate(),logEntryRequest.getQuantity());
        dailyIntake.setDate(logEntryRequest.getDate());
        dailyIntake.setUser(user);
        dailyIntake.setFood(food);
        dailyIntake.setQuantity(logEntryRequest.getQuantity());
        dailyIntakeService.logDailyIntake(dailyIntake);
        logEntryRequest.setIntakeId(dailyIntake.getIntakeId());



        for (AdjustedNutrient nutrient : adjustedNutrients) {
            LogEntry logEntry = new LogEntry();
            logEntry.setNutrient(nutrientRepository.getById(nutrient.getNutrientId()));
            logEntry.setAmount(nutrient.getAmount());
            logEntry.setPercentDailyValue(nutrient.getDailyPercentage());
            logEntry.setLogDate(logEntryRequest.getDate());
            logEntry.setUser(user);
            logEntry.setDailyIntake(dailyIntake);

            dailyIntakeService.saveLogEntry(logEntry);
        }


        return ResponseEntity.ok("Log entry created successfully");
    }

    @GetMapping("/getFoodByDate")
    public ResponseEntity<List<Object[]>> listFoodsByUserAndDate(@RequestParam Long userId, @RequestParam LocalDate date){

        return ResponseEntity.ok(dailyIntakeService.listFoodsByUserAndDate(userService.getUserById(userId), date));
    }

    @PostMapping("/updateLog")
    public ResponseEntity<String> updateLog(@RequestBody Map<String, String> payload) {
        Long intakeId = Long.parseLong(payload.get("intakeId"));
        Float quantity = Float.parseFloat(payload.get("quantity"));
        Float updateProportion= Float.parseFloat(payload.get("updateProportion"));

        Integer rowsUpdated = dailyIntakeService.updateLog(intakeId, quantity, updateProportion);
        return ResponseEntity.ok("entries updated = " + rowsUpdated);
    }

    @DeleteMapping("/deleteLog")
    public ResponseEntity<String> deleteLog(@RequestParam Long intakeId) {

        dailyIntakeService.deleteLog(intakeId);
        return ResponseEntity.ok("entries deleted");
    }

}

