package com.example.demo.service.auth.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterRequest {
    @NotBlank
    public String email;
    public String phone;
    @NotBlank
    public String lastName;
    @NotBlank
    public String firstName;
    @NotBlank
    public String dob;
}
