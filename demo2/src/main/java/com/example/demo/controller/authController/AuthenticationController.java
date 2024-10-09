package com.example.demo.controller.authController;


import com.example.demo.service.auth.AuthenticationService;
import com.example.demo.service.auth.Request.AuthenticationRequest;
import com.example.demo.service.auth.Request.RegisterRequest;
import com.example.demo.service.auth.response.AuthenticationResponse;
import com.example.demo.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
        AuthenticationResponse authResponse = authenticationService.register(request);

        if (authResponse == null)
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {
        System.out.print(request);
        return ResponseEntity.ok(authenticationService.authentication(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authLogin(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.checkUserExist(request);
        if (!result) {
            return ResponseEntity.ok(false);
        } return ResponseEntity.ok(authenticationService.authentication(request));
    }

    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail() {
        userService.verifyEmail();
        return ResponseEntity.ok(true);
    }
}
