package com.satishmankar.eventpass.DTO;

import com.satishmankar.eventpass.Enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data @NoArgsConstructor @AllArgsConstructor
public class UpdatePropertyRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private Long propertyId;
    @NotBlank
    private Integer totalRooms;
    @NotBlank
    private PropertyType type;
    @NotBlank
    private boolean isActive;
    @NotBlank
    private boolean isAvailable;
}
