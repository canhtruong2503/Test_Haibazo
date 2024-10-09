package com.example.demo.model;

import com.example.demo.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User  implements UserDetails {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private String uuid;
 @Column(nullable = false)
 private String firstName;
 @Column(nullable = false)
 private String lastName;
 private String avatar;
 @Column(nullable = false)
 private String email;
 @Column(nullable = false)
 private String phone;
 private Date dob;
 private boolean verifyEmail;
 private LocalDate createDate = LocalDate.now();
 @Enumerated(EnumType.STRING)
 private Role role;
 private String password;
 @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
 @JsonIgnore
 private List<Product> products;
@OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
@JsonIgnore
private List<FavoriteProduct> favoriteProducts;
 @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
 @JsonIgnore
 private List<Bill> bills;

 @Override
 public Collection<? extends GrantedAuthority> getAuthorities() {
  return List.of(new SimpleGrantedAuthority(role.name()));
 }

 @Override
 public String getPassword() {
  return password;
 }

 @Override
 public String getUsername() {
  return email;
 }
}
