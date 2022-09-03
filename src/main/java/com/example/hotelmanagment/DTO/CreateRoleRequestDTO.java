package com.example.hotelmanagment.DTO;

import com.example.hotelmanagment.Enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateRoleRequestDTO {
    @NotBlank
    private UserRole role;
    @NotBlank
    private String permissions;
}
