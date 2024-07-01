package com.bhanu.nutritracker.repository;

import com.bhanu.nutritracker.dto.NutrientSummary;
import com.bhanu.nutritracker.entity.DailyIntake;
import com.bhanu.nutritracker.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
    @Query("SELECT new com.bhanu.nutritracker.dto.NutrientSummary(l.nutrient.id, l.nutrient.nutrientName, l.nutrient.nutrientUnitName, CAST(SUM(l.amount) AS integer), CAST(SUM(l.percentDailyValue) AS integer)) " +
            "FROM LogEntry l " +
            "WHERE l.user.userId = :userId AND l.logDate = :date " +
            "GROUP BY l.nutrient.id, l.nutrient.nutrientName, l.nutrient.nutrientUnitName, l.nutrient.nutrientRank " +
            "ORDER BY l.nutrient.nutrientRank")

    List<NutrientSummary> getNutrientSummaryByUserIdAndLogDate(@Param("userId") Long userId, @Param("date") LocalDate date);

    @Modifying
    @Query("delete from LogEntry l where l.dailyIntake= :intake")
    void delete(DailyIntake intake);

    @Modifying
    @Query("update LogEntry l set l.amount=l.amount* :updatedProportion, l.percentDailyValue=l.percentDailyValue* :updatedProportion where l.dailyIntake= :intake")
    void updateLogEntry(DailyIntake intake, Float updatedProportion);

    // Define custom query methods if needed
}
