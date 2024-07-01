package com.bhanu.nutritracker.services;

import com.bhanu.nutritracker.entity.User;
import com.bhanu.nutritracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }


    public String getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return String.valueOf(authentication);
            // Assuming you have a UserDetails implementation with the userId accessible
//            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//            return userRepository.findByUsername(userDetails.getUsername()).getUserId();
        }
        return null; // or handle appropriately if no user is authenticated
    }
}

