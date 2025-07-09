package com.nccformation.service;

import com.nccformation.dto.RegisterRequest;
import com.nccformation.entity.User;
import com.nccformation.exception.ResourceNotFoundException;
import com.nccformation.exception.UserAlreadyExistsException;
import com.nccformation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé: " + email));
    }
    
    public User createUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Un utilisateur avec cet email existe déjà");
        }
        
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setRole(User.Role.USER);
        
        return userRepository.save(user);
    }
    
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'ID: " + id));
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'email: " + email));
    }
    
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public List<User> findUsersByRole(User.Role role) {
        return userRepository.findByRole(role);
    }
    
    public User updateUser(Long id, User userDetails) {
        User user = findById(id);
        
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPhone(userDetails.getPhone());
        
        return userRepository.save(user);
    }
    
    public void deleteUser(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }
    
    public long getTotalUsers() {
        return userRepository.count();
    }
    
    public long getUsersByRole(User.Role role) {
        return userRepository.countByRole(role);
    }
}