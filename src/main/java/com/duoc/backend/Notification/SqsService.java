package com.duoc.backend.Notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Service
public class SqsService {

    private final SqsClient sqsClient;
    private final String queueUrl;

    public SqsService(
            @Value("${aws.region:us-east-1}") String region,
            @Value("${aws.sqs.queue.url}") String queueUrl) {
        this.sqsClient = SqsClient.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
        this.queueUrl = queueUrl;
    }

    public String sendMessage(String messageBody) {
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .build();
        SendMessageResponse response = sqsClient.sendMessage(request);
        return response.messageId();
    }
}
