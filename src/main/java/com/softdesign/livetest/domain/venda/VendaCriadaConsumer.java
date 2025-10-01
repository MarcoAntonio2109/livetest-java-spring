package com.softdesign.livetest.domain.venda;

import com.softdesign.livetest.api.venda.VendaCriadaMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class VendaCriadaConsumer {

    @KafkaListener(topics = "venda-criada", groupId = "livetest-group")
    public void consume(VendaCriadaMessage message) {
        System.out.println("Venda criada recebida: " + message);

        // Aqui pode acionar outros futuros como faturamento, estoque e outros
    }
}
