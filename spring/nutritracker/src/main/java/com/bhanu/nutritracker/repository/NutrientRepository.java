package com.bhanu.nutritracker.repository;

import com.bhanu.nutritracker.dto.NutrientDetail;
import com.bhanu.nutritracker.entity.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutrientRepository extends JpaRepository<Nutrient, Long> {
@Query("select new com.bhanu.nutritracker.dto.NutrientDetail(n.id,n.nutrientName, n.nutrientUnitName, 0, 0) from Nutrient n where lower(n.nutrientName) like lower(concat('%', :searchTerm, '%'))  order by n.nutrientRank limit 5")
    List<NutrientDetail> getAllNutrients(String searchTerm);
}
