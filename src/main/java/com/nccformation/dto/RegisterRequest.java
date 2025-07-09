package com.nccformation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    
    @NotBlank(message = "Prénom est obligatoire")
    @Size(max = 50, message = "Prénom ne peut pas dépasser 50 caractères")
    private String firstName;
    
    @NotBlank(message = "Nom est obligatoire")
    @Size(max = 50, message = "Nom ne peut pas dépasser 50 caractères")
    private String lastName;
    
    @NotBlank(message = "Email est obligatoire")
    @Email(message = "Format email invalide")
    @Size(max = 100, message = "Email ne peut pas dépasser 100 caractères")
    private String email;
    
    @NotBlank(message = "Mot de passe est obligatoire")
    @Size(min = 6, max = 100, message = "Mot de passe doit contenir entre 6 et 100 caractères")
    private String password;
    
    @Size(max = 20, message = "Téléphone ne peut pas dépasser 20 caractères")
    private String phone;
    
    // Constructors
    public RegisterRequest() {}
    
    public RegisterRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    
    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}