package com.softdesign.livetest.domain.venda.adiciolnaritemvenda;

import com.softdesign.livetest.applicationservice.venda.AdicionarItemVendaApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdicionarItemVendaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdicionarItemVendaProducer.class);

    private final KafkaTemplate<String, AdicionarItemVendaMessage> kafkaTemplate;

    public AdicionarItemVendaProducer(
            AdicionarItemVendaApplicationService adicionarItemVendaApplicationService,
            KafkaTemplate<String, AdicionarItemVendaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(AdicionarItemVendaMessage adicionarItemVendaMessage) {

        LOGGER.info("#### tentando enviar mensagem [{}]", adicionarItemVendaMessage);

        var completableFuture = kafkaTemplate.send("adicionar-item-venda", adicionarItemVendaMessage);

        completableFuture.whenComplete((sendResult, throwable) -> {
            if (throwable != null) {
                LOGGER.error("#### erro ao tentar enviar mensangem [{}]", throwable.getMessage(), throwable);
                completableFuture.completeExceptionally(throwable);
            } else {
                LOGGER.info("#### sendResult [{}]", sendResult);
                completableFuture.complete(sendResult);
            }
        });
    }

}
