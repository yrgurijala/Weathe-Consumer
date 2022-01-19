package com.egen.WeatherConsumer;

import com.egen.WeatherConsumer.awsMessaging.WeatherAlertSqs;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WeatherConsumerApplication {

	public static void main(String[] args) throws JsonProcessingException {
		ApplicationContext applicationContext = SpringApplication.run(WeatherConsumerApplication.class, args);
		WeatherAlertSqs weatherAlertSqs = applicationContext.getBean(WeatherAlertSqs.class);
		weatherAlertSqs.startListeningToMessages();
	}

}
