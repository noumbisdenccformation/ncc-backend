package com.nccformation.controller;

import com.nccformation.dto.AuthRequest;
import com.nccformation.dto.AuthResponse;
import com.nccformation.dto.RegisterRequest;
import com.nccformation.entity.User;
import com.nccformation.security.JwtUtils;
import com.nccformation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "API d'authentification")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @PostMapping("/login")
    @Operation(summary = "Connexion utilisateur")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        
        AuthResponse response = new AuthResponse(
            token,
            user.getId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getRole().name()
        );
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register")
    @Operation(summary = "Inscription utilisateur")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        User user = userService.createUser(request);
        String token = jwtUtils.generateToken(user);
        
        AuthResponse response = new AuthResponse(
            token,
            user.getId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getRole().name()
        );
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/me")
    @Operation(summary = "Profil utilisateur connecté")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }
}