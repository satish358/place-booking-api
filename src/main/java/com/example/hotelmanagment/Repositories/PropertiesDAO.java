package com.example.hotelmanagment.Repositories;

import com.example.hotelmanagment.Enums.PropertyType;
import com.example.hotelmanagment.Models.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertiesDAO extends JpaRepository<Properties, Long> {
    List<Properties> findByAddressContainingIgnoreCase(String place);
    List<Properties> findAllByType(PropertyType type);
}
