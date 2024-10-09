package com.example.demo.service.user;

import com.example.demo.config.JwtService;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return userRepository.findByEmail(userEmail).orElseThrow();
    }
    public void verifyEmail() {
        User user = getCurrentUser();
        System.out.println(user);
        user.setVerifyEmail(true);
        userRepository.save(user);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
