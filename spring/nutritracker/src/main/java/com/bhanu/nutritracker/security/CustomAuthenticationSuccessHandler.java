package com.bhanu.nutritracker.security;

import com.bhanu.nutritracker.entity.User;
import com.bhanu.nutritracker.repository.UserRepository;
import com.bhanu.nutritracker.services.DailyIntakeService;
import com.bhanu.nutritracker.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(DailyIntakeService.class);
    private final UserService userService;

    @Autowired
    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Assuming you have a method to get userId, replace with actual method
        User user = userService.findByUsername(userDetails.getUsername());
        Long userId = user.getUserId();
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("{\"message\": \"Login successful\", \"userId\": " + user.getUserId() + "}");
        response.getWriter().flush();
    }
}
