package com.nccformation.service;

import com.nccformation.entity.Formation;
import com.nccformation.exception.ResourceNotFoundException;
import com.nccformation.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class FormationService {
    
    @Autowired
    private FormationRepository formationRepository;
    
    public Formation createFormation(Formation formation) {
        return formationRepository.save(formation);
    }
    
    public Formation findById(Long id) {
        return formationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Formation non trouvée avec l'ID: " + id));
    }
    
    public List<Formation> findAllFormations() {
        return formationRepository.findAll();
    }
    
    public List<Formation> findActiveFormations() {
        return formationRepository.findActiveFormations();
    }
    
    public List<Formation> findByCategory(Formation.Category category) {
        return formationRepository.findByCategory(category);
    }
    
    public List<Formation> findByInstructor(Long instructorId) {
        return formationRepository.findByInstructorId(instructorId);
    }
    
    public List<Formation> searchFormations(String keyword) {
        return formationRepository.searchByKeyword(keyword);
    }
    
    public List<Formation> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return formationRepository.findByPriceRange(minPrice, maxPrice);
    }
    
    public Formation updateFormation(Long id, Formation formationDetails) {
        Formation formation = findById(id);
        
        formation.setTitle(formationDetails.getTitle());
        formation.setDescription(formationDetails.getDescription());
        formation.setPrice(formationDetails.getPrice());
        formation.setDuration(formationDetails.getDuration());
        formation.setLevel(formationDetails.getLevel());
        formation.setImageUrl(formationDetails.getImageUrl());
        formation.setCategory(formationDetails.getCategory());
        formation.setStatus(formationDetails.getStatus());
        formation.setMaxStudents(formationDetails.getMaxStudents());
        
        return formationRepository.save(formation);
    }
    
    public void deleteFormation(Long id) {
        Formation formation = findById(id);
        formationRepository.delete(formation);
    }
    
    public long getTotalFormations() {
        return formationRepository.count();
    }
    
    public long getActiveFormationsCount() {
        return formationRepository.countActiveFormations();
    }
    
    public Formation enrollStudent(Long formationId) {
        Formation formation = findById(formationId);
        
        if (formation.getCurrentStudents() >= formation.getMaxStudents()) {
            throw new RuntimeException("Formation complète");
        }
        
        formation.setCurrentStudents(formation.getCurrentStudents() + 1);
        return formationRepository.save(formation);
    }
}