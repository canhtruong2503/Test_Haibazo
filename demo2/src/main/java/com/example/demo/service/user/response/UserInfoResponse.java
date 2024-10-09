package com.example.demo.service.user.response;


import com.example.demo.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoResponse {
    public int id;
    public String email;
    public String firstName;
    public String lastName;
    public String phone;
    public String address;
    public String avatar;
    public com.example.demo.model.enums.Role role;
    public LocalDate createDate;
}
