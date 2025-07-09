package com.nccformation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "formations")
public class Formation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(length = 200)
    private String title;
    
    @Column(length = 1000)
    private String description;
    
    @NotNull
    @Positive
    private BigDecimal price;
    
    private String currency = "FCFA";
    
    @NotBlank
    private String duration;
    
    @NotBlank
    private String level;
    
    private String imageUrl;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private User instructor;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    
    private int maxStudents = 100;
    private int currentStudents = 0;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    // Constructors
    public Formation() {}
    
    public Formation(String title, String description, BigDecimal price, String duration, String level) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.level = level;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public User getInstructor() { return instructor; }
    public void setInstructor(User instructor) { this.instructor = instructor; }
    
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    
    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }
    
    public int getCurrentStudents() { return currentStudents; }
    public void setCurrentStudents(int currentStudents) { this.currentStudents = currentStudents; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    public enum Category {
        DEVELOPPEMENT, MARKETING, CRYPTO, DESIGN, ENTREPRENEURIAT
    }
    
    public enum Status {
        ACTIVE, INACTIVE, COMING_SOON, COMPLETED
    }
}