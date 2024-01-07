package com.example.ecommerce.Auth;


import com.example.ecommerce.User.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String Name;
    String username;
    String email;
    String password;
    Rol rol;
}