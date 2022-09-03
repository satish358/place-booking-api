package com.example.hotelmanagment.Services;

import com.example.hotelmanagment.Enums.FeatureEnum;
import com.example.hotelmanagment.Models.Properties;
import com.example.hotelmanagment.Models.PropertyFeature;
import com.example.hotelmanagment.Repositories.PropertyFeatureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureService {
    @Autowired
    PropertyFeatureDAO propertyFeatureDAO;

    public void saveAll(FeatureEnum[] features, Long propertiesId){
        for(FeatureEnum featureEnum : features){
            PropertyFeature propertyFeature = new PropertyFeature();
            propertyFeature.setPropertyId(propertiesId);
            propertyFeature.setFeature(featureEnum);
            propertyFeatureDAO.saveAndFlush(propertyFeature);
        }
    }
}
