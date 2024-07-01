package com.bhanu.nutritracker.repository;

import com.bhanu.nutritracker.entity.DailyIntake;
import com.bhanu.nutritracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyIntakeRepository extends JpaRepository<DailyIntake, Long> {


    @Query("SELECT d.food.foodName,d.quantity, d.intakeId FROM DailyIntake d WHERE d.date = :date AND d.user = :user")
    List<Object[]> listFoodsByUserAndDate(@Param("user") User user, @Param("date") LocalDate date);

    @Modifying
    @Query("update DailyIntake d set d.quantity= :quantity where d.intakeId= :intakeId")
    Integer updateLog(Long intakeId, Float quantity);

//    List<DailyIntake> listFoodsByUserAndDate(User user, LocalDate date);


}
