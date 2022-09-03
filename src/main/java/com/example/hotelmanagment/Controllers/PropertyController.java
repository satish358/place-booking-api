package com.example.hotelmanagment.Controllers;

import com.example.hotelmanagment.Enums.FeatureEnum;
import com.example.hotelmanagment.Models.Properties;
import com.example.hotelmanagment.Services.FeatureService;
import com.example.hotelmanagment.Services.PropertiesService;
import com.example.hotelmanagment.DTO.BasicResponseDTO;
import com.example.hotelmanagment.DTO.CreatePropertyRequestDTO;
import com.example.hotelmanagment.DTO.UpdatePropertyRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    PropertiesService propertiesService;
    @Autowired
    FeatureService featureService;

    @GetMapping("")
    @Operation(summary = "It will shows all properties, page number start with 0 zero", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Page<Properties>> getAllProperties(@RequestParam int pageNumber, @RequestParam int recordsPerPage){
        Page<Properties> page = propertiesService.getAllProperties(pageNumber, recordsPerPage);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
    @GetMapping("/{propertyId}")
    @Operation(summary = "It will shows single property", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<BasicResponseDTO<Properties>> getProperty(@PathVariable(value = "propertyId") long propertyId){

        Optional<Properties> _property = propertiesService.getProperty(propertyId);
        if(_property.isPresent()){
            return new ResponseEntity<>(new BasicResponseDTO<>(true, "Your data", _property.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new BasicResponseDTO<>(false, "No records present", null ), HttpStatus.OK);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Used to add property", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<BasicResponseDTO<Properties>> createProperty(@ModelAttribute CreatePropertyRequestDTO createPropertyRequestDTO){
        Properties properties = new Properties();
        properties.setType(createPropertyRequestDTO.getType());
        properties.setName(createPropertyRequestDTO.getName());
        properties.setAddress(createPropertyRequestDTO.getAddress());

        properties.setPrice(createPropertyRequestDTO.getPrice());
        Properties savedData = propertiesService.createProperty(properties, createPropertyRequestDTO.getBanner(), createPropertyRequestDTO.getUserId());
        if(createPropertyRequestDTO.getFeatures().length > 0) {
            featureService.saveAll(createPropertyRequestDTO.getFeatures(), savedData.getId());
        }
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Property saved", savedData), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    @Operation(summary = "Used to update property", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<BasicResponseDTO<Properties>> updateProperty(@RequestBody UpdatePropertyRequestDTO updatePropertyRequestDTO){
        Optional<Properties> savedData = propertiesService.updateProperty(updatePropertyRequestDTO);
        if(savedData.isEmpty())
            return new ResponseEntity<>(new BasicResponseDTO<>(false, "Property not found", null), HttpStatus.OK);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Property updated", savedData.get()), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{propertyId}")
    @Operation(summary = "It will shows single property", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<BasicResponseDTO<Properties>> deleteProperty(@PathVariable(value = "propertyId") long propertyId){
         propertiesService.deleteProperty(propertyId);
        return new ResponseEntity<>(new BasicResponseDTO<>(true, "Property Deleted", null ), HttpStatus.OK);
    }
}
// eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYXRpc2htQGdtYWlsLmNvbSIsImV4cCI6MTY2MjE2Mjc2OSwiaWF0IjoxNjYyMTQ0NzY5fQ.Iucmr-axs4XllEhqYYIl3xKmk4CFIxqXyctcSfuE_rVs6sbSLFW7dIfKotqYuPa3UE7BQmlf5dM3bVG4fzquDw