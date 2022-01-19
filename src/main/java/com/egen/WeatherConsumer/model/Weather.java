package com.egen.WeatherConsumer.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Data
@Entity
public class Weather {

    @Id
    private String id;
    private String city;
    private String description;
    private double humidity;
    private double pressure;
    private double temperature;
    @OneToOne(cascade = CascadeType.ALL)
    private Wind wind;
    private Timestamp timestamp;
}

/*
{
        "city": "Chicago",
        "description": "scattered clouds",
        "humidity": 33,
        "pressure": 1020,
        "temperature": 25,
        "wind": {
        "speed": 3.1,
        "degree": 240
        },
        "timestamp": "2017-02-14T05:48:41.861Z"
        }
*/
