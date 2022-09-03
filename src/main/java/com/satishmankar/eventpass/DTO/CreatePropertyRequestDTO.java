package com.satishmankar.eventpass.DTO;

import com.satishmankar.eventpass.Enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreatePropertyRequestDTO {
    private String name;
    private String address;
    private Long userId;
    private PropertyType type;
}
