package com.example.hotelmanagment.Repositories;

import com.example.hotelmanagment.Models.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDAO extends JpaRepository<Images, Long> {
}
