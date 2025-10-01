package com.softdesign.livetest.domain.venda.adiciolnaritemvenda;

import com.softdesign.livetest.applicationservice.venda.AdicionarItemVendaApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AdicionarItemVendaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdicionarItemVendaProducer.class);

    private final KafkaTemplate<String, AdicionarItemVendaMessage> kafkaTemplate;

    private final RetryTemplate retryTemplate;

    public AdicionarItemVendaProducer(
            AdicionarItemVendaApplicationService adicionarItemVendaApplicationService,
            KafkaTemplate<String, AdicionarItemVendaMessage> kafkaTemplate,
            RetryTemplate retryTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.retryTemplate = retryTemplate;
    }


//    public void send(AdicionarItemVendaMessage adicionarItemVendaMessage) {
//
//        LOGGER.info("#### tentando enviar mensagem [{}]", adicionarItemVendaMessage);
//
//        var completableFuture = kafkaTemplate.send("adicionar-item-venda", adicionarItemVendaMessage);
//
//        completableFuture.whenComplete((sendResult, throwable) -> {
//            if (throwable != null) {
//                LOGGER.error("#### erro ao tentar enviar mensangem [{}]", throwable.getMessage(), throwable);
//                completableFuture.completeExceptionally(throwable);
//            } else {
//                LOGGER.info("#### sendResult [{}]", sendResult);
//                completableFuture.complete(sendResult);
//            }
//        });
//    }

    public void send(AdicionarItemVendaMessage message) {

        if (message == null || message.vendId() == null || message.produtoId() == null || message.quantidade() <= 0) {
            LOGGER.warn("Mensagem inválida. Ignorando envio: {}", message);
            return;
        }

        retryTemplate.execute(context -> {
            CompletableFuture<SendResult<String, AdicionarItemVendaMessage>> future =
                    kafkaTemplate.send("adicionar-item-venda", message);

            future.whenComplete((result, ex) -> {
                if (ex != null) {
                    LOGGER.error("Falha ao enviar mensagem: {}", message, ex);
                } else {
                    LOGGER.info("Mensagem enviada com sucesso: {}", result.getRecordMetadata());
                }
            });

            return null;
        }, context -> {
            LOGGER.error("Todas as tentativas falharam. Enviando para DLT: {}", message);
            kafkaTemplate.send("adicionar-item-venda-dlt", message);
            return null;

        });
    }

}
