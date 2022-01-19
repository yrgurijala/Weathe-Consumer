package com.egen.WeatherConsumer.repo;

import com.egen.WeatherConsumer.model.WeatherAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherAlertRepo extends JpaRepository<WeatherAlert, String> {
}
