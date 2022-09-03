package com.example.hotelmanagment.Controllers;

import com.example.hotelmanagment.DTO.BasicResponseDTO;
import com.example.hotelmanagment.DTO.SearchRequestDTO;
import com.example.hotelmanagment.Enums.SearchByEnum;
import com.example.hotelmanagment.Models.PropertyFeature;
import com.example.hotelmanagment.Repositories.PropertiesDAO;
import com.example.hotelmanagment.Repositories.PropertyFeatureDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@CrossOrigin
@RestController
@RequestMapping("api/search")
public class SearchController {
    @Autowired
    PropertiesDAO propertiesDAO;
    @Autowired
    PropertyFeatureDAO propertyFeatureDAO;
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("")
    public ResponseEntity<BasicResponseDTO<List<Properties>>> searchProperties(@RequestBody SearchRequestDTO searchRequestDTO){
        BasicResponseDTO basicResponseDTO = new BasicResponseDTO<>( true, "Records found", null);
        if(searchRequestDTO.getSearchBy().equals(SearchByEnum.PLACE)){
            basicResponseDTO.setData(
                    propertiesDAO.findByAddressContainingIgnoreCase(searchRequestDTO.getPlaceToVisit())
            );
        }
        if(searchRequestDTO.getSearchBy().equals(SearchByEnum.TYPE_OF_PROPERTY)){
            basicResponseDTO.setData(
                    propertiesDAO.findAllByType(searchRequestDTO.getPropertyType())
            );
        }
        if(searchRequestDTO.getSearchBy().equals(SearchByEnum.FEATURE)) {
            List<PropertyFeature> features = propertyFeatureDAO.getPropertyFeatureByFeature(searchRequestDTO.getFeature());
            List<Long> properties = new ArrayList<>();
            features.forEach(feature -> properties.add(feature.getPropertyId()));
            basicResponseDTO.setData(propertiesDAO.findAllById(properties));
        }

        return new ResponseEntity<>(basicResponseDTO,HttpStatus.OK);
    }
}
