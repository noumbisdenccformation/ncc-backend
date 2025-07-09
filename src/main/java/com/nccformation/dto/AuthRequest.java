package com.nccformation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthRequest {
    
    @NotBlank(message = "Email est obligatoire")
    @Email(message = "Format email invalide")
    private String email;
    
    @NotBlank(message = "Mot de passe est obligatoire")
    @Size(min = 6, message = "Mot de passe doit contenir au moins 6 caractères")
    private String password;
    
    // Constructors
    public AuthRequest() {}
    
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}