package com.softdesign.livetest.domain.venda.adiciolnaritemvenda;

import com.softdesign.livetest.applicationservice.venda.AdicionarItemVendaApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AdicionarItemVendaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdicionarItemVendaConsumer.class);

    private final AdicionarItemVendaApplicationService adicionarItemVendaApplicationService;

    public AdicionarItemVendaConsumer(AdicionarItemVendaApplicationService adicionarItemVendaApplicationService) {
        this.adicionarItemVendaApplicationService = adicionarItemVendaApplicationService;
    }

    @KafkaListener(topics = "adicionar-item-venda", groupId = "livetest-java-spring-group")
    public void consume(AdicionarItemVendaMessage adicionarItemVendaMessage) {

        try {
            LOGGER.info("#### -> Consumed message -> {}", adicionarItemVendaMessage);

            adicionarItemVendaApplicationService.executar(
                    adicionarItemVendaMessage.vendId(),
                    adicionarItemVendaMessage.produtoId(),
                    adicionarItemVendaMessage.quantidade());
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
        }
    }

}
