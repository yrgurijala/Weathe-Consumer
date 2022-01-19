package com.egen.WeatherConsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@Entity
public class WeatherAlert {

    @Id
    private String id;
    private String alertStatus;
    @OneToOne(cascade = CascadeType.ALL)
    private Weather weather;

    public WeatherAlert(){
        this.id = UUID.randomUUID().toString();
    }
}
