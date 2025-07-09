package com.nccformation.repository;

import com.nccformation.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    
    List<Formation> findByStatus(Formation.Status status);
    
    List<Formation> findByCategory(Formation.Category category);
    
    List<Formation> findByInstructorId(Long instructorId);
    
    @Query("SELECT f FROM Formation f WHERE f.status = 'ACTIVE' ORDER BY f.createdAt DESC")
    List<Formation> findActiveFormations();
    
    @Query("SELECT f FROM Formation f WHERE f.price BETWEEN :minPrice AND :maxPrice")
    List<Formation> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    
    @Query("SELECT f FROM Formation f WHERE LOWER(f.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(f.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Formation> searchByKeyword(String keyword);
    
    @Query("SELECT COUNT(f) FROM Formation f WHERE f.status = 'ACTIVE'")
    long countActiveFormations();
}