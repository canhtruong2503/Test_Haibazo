package com.example.demo.service.auth;

import com.example.demo.model.User;
import com.example.demo.model.enums.Role;
import com.example.demo.service.auth.Request.AuthenticationRequest;
import com.example.demo.service.auth.Request.RegisterRequest;
import com.example.demo.service.auth.response.AuthenticationResponse;
import com.example.demo.service.user.response.UserInfoResponse;
import com.example.demo.util.AppUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final com.example.demo.repository.UserRepository userRepository;
    private final com.example.demo.config.JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(@Valid RegisterRequest request) {

        String phone = "+84" + request.getPhone();
        if (userRepository.findByEmail(request.getEmail()).isPresent() || userRepository.findByPhone(phone).isPresent()) {
            return null;
        }
        User user = null;
        String uuid = UUID.randomUUID().toString();
        try {
            user = User.builder()
                    .email(request.getEmail())
                    .phone(phone)
                    .firstName(request.firstName)
                    .lastName(request.lastName)
                    .uuid(uuid)
                    .createDate(LocalDate.now())
                    .password(passwordEncoder.encode(uuid))
                    .dob(new SimpleDateFormat("yyyy-MM-dd").parse(request.getDob()))
                    .role(Role.USER)
                    .build();

        } catch (ParseException e) {
            System.out.printf("a");
            return null;
        }
        System.out.printf(user.toString()+"aa");
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userInfo(AppUtils.mapper.map(user, UserInfoResponse.class))
                .build();
    }

    public AuthenticationResponse authentication(AuthenticationRequest request) {
        var user = userRepository.findUserByEmailOrPhone(request.getValue(), request.getValue()).orElseThrow();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getUuid()
                )
        );
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public boolean checkUserExist(AuthenticationRequest request) {
        var user = userRepository.findUserByEmailOrPhone(request.getValue(), request.getValue());
        return user.isPresent() ? true : false;
    }
}
