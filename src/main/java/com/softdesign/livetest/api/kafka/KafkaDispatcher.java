package com.softdesign.livetest.api.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaDispatcher<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    public KafkaDispatcher(KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void dispatch(String topic, T payload) {
        kafkaTemplate.send(topic, payload).whenComplete((result, ex) -> {
            if (ex != null) {
                // log ou enviar para Dead Letter Topic
                System.err.println("Erro ao enviar para Kafka: " + ex.getMessage());
            } else {
                System.out.println("Mensagem enviada para Kafka: " + result.getRecordMetadata());
            }
        });
    }
}
