package com.egen.WeatherConsumer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Wind {

    @Id
    private String id;
    private double speed;
    private double degree;
}
