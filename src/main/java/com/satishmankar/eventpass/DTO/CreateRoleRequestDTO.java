package com.satishmankar.eventpass.DTO;

import com.satishmankar.eventpass.Enums.UserRole;
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
