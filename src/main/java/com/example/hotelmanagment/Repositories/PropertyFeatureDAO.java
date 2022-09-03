package com.example.hotelmanagment.Repositories;

import com.example.hotelmanagment.Enums.FeatureEnum;
import com.example.hotelmanagment.Models.PropertyFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyFeatureDAO extends JpaRepository<PropertyFeature, Long> {
    List<PropertyFeature> getPropertyFeatureByFeature(FeatureEnum featureEnum);
}
