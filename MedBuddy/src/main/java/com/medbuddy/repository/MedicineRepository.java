package com.medbuddy.repository;

import com.medbuddy.model.Medicine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
	
	@Query("SELECT m FROM Medicine m WHERE LOWER(m.medName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(m.category.categoryName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Medicine> searchMedicines(@Param("keyword") String keyword);
    //Set<Medicine> searchMedicines(@Param("keyword") String keyword);
	
	List<Medicine> findByOrderByMedNameAsc();

	List<Medicine> findByOrderByMedNameDesc();

	List<Medicine> findByCategoryCategoryNameIgnoreCase(String categoryName);
}
