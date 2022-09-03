package com.example.hotelmanagment.Repositories;

import com.example.hotelmanagment.Models.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesDAO extends JpaRepository<Properties, Long> {
}
