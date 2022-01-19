package com.egen.WeatherConsumer.controller;

import com.egen.WeatherConsumer.model.WeatherAlert;
import com.egen.WeatherConsumer.service.WeatherAlertService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weatherAlert")
public class WeatherAlertController {

    private WeatherAlertService weatherAlertService;

    public WeatherAlertController(WeatherAlertService weatherAlertService){
        this.weatherAlertService = weatherAlertService;
    }

    @PostMapping("/addData")
    public boolean addData(@RequestBody WeatherAlert weatherAlert){
        weatherAlertService.addWeatherAlertReading(weatherAlert);
        return true;
    }

}
