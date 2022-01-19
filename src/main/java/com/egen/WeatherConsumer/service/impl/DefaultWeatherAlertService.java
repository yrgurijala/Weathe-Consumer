package com.egen.WeatherConsumer.service.impl;

import com.egen.WeatherConsumer.model.WeatherAlert;
import com.egen.WeatherConsumer.repo.WeatherAlertRepo;
import com.egen.WeatherConsumer.service.WeatherAlertService;
import org.springframework.stereotype.Service;

@Service
public class DefaultWeatherAlertService implements WeatherAlertService {

    WeatherAlertRepo weatherAlertRepo;

    public DefaultWeatherAlertService(WeatherAlertRepo weatherAlertRepo){
        this.weatherAlertRepo = weatherAlertRepo;
    }

    @Override
    public boolean addWeatherAlertReading(WeatherAlert weatherAlert) {
        System.out.println(weatherAlert);
        weatherAlertRepo.save(weatherAlert);
        return false;
    }
}
