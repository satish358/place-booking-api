package com.example.hotelmanagment.Services;

import com.example.hotelmanagment.Enums.FeatureEnum;
import com.example.hotelmanagment.Models.Properties;
import com.example.hotelmanagment.Repositories.PropertiesDAO;
import com.example.hotelmanagment.DTO.UpdatePropertyRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Optional;

@Service
public class PropertiesService {
    @Autowired
    PropertiesDAO propertiesDAO;
    @Autowired
    FilesStorageService storageService;
    @Value("${files.upload.url}")
    private String filesUrl;

    public Page<Properties> getAllProperties(int page, int size) {
        return propertiesDAO.findAll(PageRequest.of(page, size));
    }
    public Optional<Properties> getProperty(Long propertyId){
        return propertiesDAO.findById(propertyId);
    }
    public Properties createProperty(Properties properties, MultipartFile banner){
        Optional<String> fileName =  storageService.save(banner);
        if(fileName.isPresent())
            properties.setBannerUrl(filesUrl+fileName.get());

        properties.setCreatedOn(new Date());
        properties.setActive(true);
        propertiesDAO.save(properties);
        return properties;
    }
    public Optional<Properties> updateProperty(UpdatePropertyRequestDTO updatePropertyRequestDTO){
        Optional<Properties> _property = propertiesDAO.findById(updatePropertyRequestDTO.getPropertyId());
        if(_property.isEmpty())
            return Optional.of(null);
        Properties property = _property.get();
        property.setType(updatePropertyRequestDTO.getType());
        property.setName(updatePropertyRequestDTO.getName());
        property.setAddress(updatePropertyRequestDTO.getAddress());
        property.setActive(updatePropertyRequestDTO.isActive());
        property.setPrice(updatePropertyRequestDTO.getPrice());
        propertiesDAO.save(property);
        return Optional.of(property);
    }
    public void deleteProperty(Long propertyId){
         propertiesDAO.deleteById(propertyId);
    }
}
