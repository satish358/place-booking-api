package com.example.hotelmanagment.DTO;

import com.example.hotelmanagment.Enums.FeatureEnum;
import com.example.hotelmanagment.Enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreatePropertyRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private Long userId;
    @NotBlank
    private Long price;
    @NotBlank
    private PropertyType type;
    private FeatureEnum[] features;
    private MultipartFile banner;
}
