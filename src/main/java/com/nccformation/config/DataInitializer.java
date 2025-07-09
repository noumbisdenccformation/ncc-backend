package com.nccformation.config;

import com.nccformation.entity.Formation;
import com.nccformation.entity.User;
import com.nccformation.repository.FormationRepository;
import com.nccformation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FormationRepository formationRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            // Créer utilisateur admin
            User admin = new User("Admin", "NCC", "admin@nccformation.com", 
                                passwordEncoder.encode("password"));
            admin.setRole(User.Role.ADMIN);
            admin.setPhone("+237677277451");
            userRepository.save(admin);
            
            // Créer utilisateur normal
            User user = new User("Jean", "Dupont", "jean@example.com", 
                               passwordEncoder.encode("password"));
            user.setPhone("+237658780293");
            userRepository.save(user);
            
            // Créer coach
            User coach = new User("Marie", "Coach", "marie@nccformation.com", 
                                passwordEncoder.encode("password"));
            coach.setRole(User.Role.COACH);
            coach.setPhone("+237699123456");
            userRepository.save(coach);
            
            System.out.println("✅ Utilisateurs créés avec succès!");
        }
        
        if (formationRepository.count() == 0) {
            // Créer formations de test
            Formation webDev = new Formation("Développement Web Full Stack", 
                "Maîtrisez HTML, CSS, JavaScript, Angular et Spring Boot", 
                new BigDecimal("179000"), "40h", "Débutant à Avancé");
            webDev.setCategory(Formation.Category.DEVELOPPEMENT);
            webDev.setImageUrl("web1.png");
            webDev.setCurrentStudents(45);
            formationRepository.save(webDev);
            
            Formation crypto = new Formation("Crypto-monnaies & Blockchain", 
                "Comprenez et investissez dans les crypto-monnaies", 
                new BigDecimal("119000"), "25h", "Débutant");
            crypto.setCategory(Formation.Category.CRYPTO);
            crypto.setImageUrl("crypto1.png");
            crypto.setCurrentStudents(32);
            formationRepository.save(crypto);
            
            Formation marketing = new Formation("Marketing Digital Avancé", 
                "Stratégies complètes pour réussir en ligne", 
                new BigDecimal("149000"), "30h", "Intermédiaire");
            marketing.setCategory(Formation.Category.MARKETING);
            marketing.setImageUrl("marketing1.png");
            marketing.setCurrentStudents(28);
            formationRepository.save(marketing);
            
            Formation edutime = new Formation("EduTime Pro", 
                "Gestion complète des emplois du temps scolaires", 
                new BigDecimal("20000"), "Illimité", "Tous niveaux");
            edutime.setCategory(Formation.Category.DEVELOPPEMENT);
            edutime.setImageUrl("edutime.png");
            edutime.setCurrentStudents(156);
            edutime.setMaxStudents(1000);
            formationRepository.save(edutime);
            
            System.out.println("✅ Formations créées avec succès!");
        }
    }
}