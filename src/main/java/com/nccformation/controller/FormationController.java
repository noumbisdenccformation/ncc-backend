package com.nccformation.controller;

import com.nccformation.entity.Formation;
import com.nccformation.service.FormationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/formations")
@Tag(name = "Formations", description = "API de gestion des formations")
@CrossOrigin(origins = "*")
public class FormationController {
    
    @Autowired
    private FormationService formationService;
    
    @GetMapping
    @Operation(summary = "Lister toutes les formations")
    public ResponseEntity<List<Formation>> getAllFormations() {
        return ResponseEntity.ok(formationService.findAllFormations());
    }
    
    @GetMapping("/active")
    @Operation(summary = "Lister les formations actives")
    public ResponseEntity<List<Formation>> getActiveFormations() {
        return ResponseEntity.ok(formationService.findActiveFormations());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une formation par ID")
    public ResponseEntity<Formation> getFormationById(@PathVariable Long id) {
        return ResponseEntity.ok(formationService.findById(id));
    }
    
    @GetMapping("/category/{category}")
    @Operation(summary = "Formations par catégorie")
    public ResponseEntity<List<Formation>> getFormationsByCategory(@PathVariable Formation.Category category) {
        return ResponseEntity.ok(formationService.findByCategory(category));
    }
    
    @GetMapping("/search")
    @Operation(summary = "Rechercher des formations")
    public ResponseEntity<List<Formation>> searchFormations(@RequestParam String keyword) {
        return ResponseEntity.ok(formationService.searchFormations(keyword));
    }
    
    @GetMapping("/price-range")
    @Operation(summary = "Formations par gamme de prix")
    public ResponseEntity<List<Formation>> getFormationsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(formationService.findByPriceRange(minPrice, maxPrice));
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    @Operation(summary = "Créer une nouvelle formation")
    public ResponseEntity<Formation> createFormation(@Valid @RequestBody Formation formation) {
        return ResponseEntity.ok(formationService.createFormation(formation));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    @Operation(summary = "Modifier une formation")
    public ResponseEntity<Formation> updateFormation(@PathVariable Long id, @Valid @RequestBody Formation formation) {
        return ResponseEntity.ok(formationService.updateFormation(id, formation));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Supprimer une formation")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        formationService.deleteFormation(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/enroll")
    @Operation(summary = "S'inscrire à une formation")
    public ResponseEntity<Formation> enrollInFormation(@PathVariable Long id) {
        return ResponseEntity.ok(formationService.enrollStudent(id));
    }
    
    @GetMapping("/stats")
    @Operation(summary = "Statistiques des formations")
    public ResponseEntity<Object> getFormationStats() {
        return ResponseEntity.ok(new Object() {
            public final long total = formationService.getTotalFormations();
            public final long active = formationService.getActiveFormationsCount();
        });
    }
}