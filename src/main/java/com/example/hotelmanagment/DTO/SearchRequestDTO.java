package com.example.hotelmanagment.DTO;

import com.example.hotelmanagment.Enums.FeatureEnum;
import com.example.hotelmanagment.Enums.PropertyType;
import com.example.hotelmanagment.Enums.SearchByEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class SearchRequestDTO {
    @NotBlank
    private SearchByEnum searchBy;
    private Date checkInDate;
    private Date checkOutDate;
    private String placeToVisit;
    private PropertyType propertyType;
    private FeatureEnum feature;
}
