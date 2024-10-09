package com.example.demo.service.auth.response;

import com.example.demo.service.user.response.UserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthenticationResponse {
    private String token;
    private UserInfoResponse userInfo;
}
