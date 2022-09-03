package com.example.hotelmanagment.Repositories;

import com.example.hotelmanagment.Models.Images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageDAO extends JpaRepository<Images, Long> {
    List<Images> findImagesByPropertyId(Long propertyId);
}
