package com.softdesign.livetest.domain.venda;

import com.softdesign.livetest.api.venda.VendaCriadaMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class VendaCriadaProducer {

    private final KafkaTemplate<String, VendaCriadaMessage> kafkaTemplate;

    public VendaCriadaProducer(KafkaTemplate<String, VendaCriadaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(VendaCriadaMessage message) {
        kafkaTemplate.send("venda-criada", message).whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Erro ao enviar venda criada: " + ex.getMessage());
            } else {
                System.out.println("Venda criada publicada: " + result.getRecordMetadata());
            }
        });
    }
}
