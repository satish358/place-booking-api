package com.example.hotelmanagment.Services;

import com.example.hotelmanagment.Enums.FeatureEnum;
import com.example.hotelmanagment.Models.Images;
import com.example.hotelmanagment.Models.Properties;
import com.example.hotelmanagment.Repositories.ImageDAO;
import com.example.hotelmanagment.Repositories.PropertiesDAO;
import com.example.hotelmanagment.DTO.UpdatePropertyRequestDTO;
import com.example.hotelmanagment.Repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PropertiesService {
    @Autowired
    PropertiesDAO propertiesDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    FilesStorageService storageService;
    @Autowired
    ImageDAO imageDAO;
    @Value("${files.upload.url}")
    private String filesUrl;

    public Page<Properties> getAllProperties(int page, int size) {
        return propertiesDAO.findAll(PageRequest.of(page, size));
    }
    public Optional<Properties> getProperty(Long propertyId){
        return propertiesDAO.findById(propertyId);
    }
    public Properties createProperty(Properties properties, MultipartFile banner, Long userId){
        Optional<String> fileName =  storageService.save(banner);
        if(fileName.isPresent())
            properties.setBannerUrl(filesUrl+fileName.get());
        properties.setUser(userDAO.getById(userId));
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
    public boolean addImage(Long propertyId, MultipartFile file){
        Optional<String> fileName =  storageService.save(file);
        Images images = new Images();
        images.setPropertyId(propertyId);
        if(fileName.isPresent()) {
            images.setImageUrl(filesUrl + fileName.get());
            imageDAO.save(images);
            return true;
        } else return false;
    }
    public List<Images> getAllImagesByProduct(Long productId){
       return imageDAO.findImagesByPropertyId(productId);
    }
    public void deleteProperty(Long propertyId){
         propertiesDAO.deleteById(propertyId);
    }
}
