package com.egen.WeatherConsumer.service;

import com.egen.WeatherConsumer.model.Weather;
import com.egen.WeatherConsumer.model.WeatherAlert;

public interface WeatherAlertService {

    boolean addWeatherAlertReading(WeatherAlert weatherAlert);
}
