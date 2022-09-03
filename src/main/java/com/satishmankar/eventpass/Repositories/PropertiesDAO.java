package com.satishmankar.eventpass.Repositories;

import com.satishmankar.eventpass.Models.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesDAO extends JpaRepository<Properties, Long> {
}
