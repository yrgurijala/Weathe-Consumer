package com.egen.WeatherConsumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SqsMessage {
    @JsonProperty("Type")
    String type;

    @JsonProperty("MessageId")
    String messageId;

    @JsonProperty("Message")
    String message;
}
