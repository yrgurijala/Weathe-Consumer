package com.egen.WeatherConsumer.awsMessaging;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.egen.WeatherConsumer.model.SqsMessage;
import com.egen.WeatherConsumer.model.WeatherAlert;
import com.egen.WeatherConsumer.service.WeatherAlertService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class WeatherAlertSqs {

    private String sqsUrl;
    private String awsAccessKey;
    private String awsSecretKey;
    private String awsRegion;
    private AmazonSQS amazonSQS;
    private ObjectMapper objectMapper;
    private WeatherAlertService weatherAlertService;

    public WeatherAlertSqs(@Value("${cloud.aws.sqs.url}") String sqsUrl,
                           @Value("${cloud.aws.credentials.access-key}") String awsAccessKey,
                           @Value("${cloud.aws.credentials.secret-key}") String awsSecretKey,
                           @Value("${cloud.aws.region.static}") String awsRegion,
                           AmazonSQS amazonSQS,
                           ObjectMapper objectMapper,
                           WeatherAlertService weatherAlertService){
        this.sqsUrl = sqsUrl;
        this.awsAccessKey = awsAccessKey;
        this.awsSecretKey = awsSecretKey;
        this.awsRegion = awsRegion;
        this.amazonSQS = amazonSQS;
        this.objectMapper = objectMapper;
        this.weatherAlertService = weatherAlertService;
    }

    @PostConstruct
    private void postConstructor() {

        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
        );

        this.amazonSQS = AmazonSQSClientBuilder.standard()
                                               .withCredentials(awsCredentialsProvider)
                                               .withRegion(awsRegion)
                                               .build();
    }

    public void startListeningToMessages() throws JsonProcessingException {

        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(sqsUrl)
                .withMaxNumberOfMessages(1)
                .withWaitTimeSeconds(3);

        while (true) {
            final List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();

            for (Message messageObject : messages) {
                String message = messageObject.getBody();

                //Converting string to Java Object
                SqsMessage sqsMessage = objectMapper.readValue(message, SqsMessage.class);
                WeatherAlert weatherAlert = objectMapper.readValue(sqsMessage.getMessage(), WeatherAlert.class);
                weatherAlertService.addWeatherAlertReading(weatherAlert);

                deleteMessage(messageObject);
            }
        }
    }

    private void deleteMessage(Message messageObject) {
        final String messageReceiptHandle = messageObject.getReceiptHandle();
        amazonSQS.deleteMessage(new DeleteMessageRequest(sqsUrl, messageReceiptHandle));
    }
}
