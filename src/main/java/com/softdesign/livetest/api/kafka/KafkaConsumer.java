package com.softdesign.livetest.api.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic", groupId = "livetest-java-spring-group")
    public void listen(String message) {
        System.out.println("📨 Kafka Consumer recebeu: " + message);
    }
}
