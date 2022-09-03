package com.example.hotelmanagment.DTO;

import com.example.hotelmanagment.Enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String  password;
    @NotBlank
    private String  confirmPassword;
    @NotBlank
    private String  masterPassword;
    @NotBlank
    private UserRole role;
}
