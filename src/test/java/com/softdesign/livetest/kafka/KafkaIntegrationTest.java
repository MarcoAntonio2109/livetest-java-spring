package com.softdesign.livetest.kafka;

import com.softdesign.livetest.api.venda.VendaCriadaMessage;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "venda-criada" })
class KafkaIntegrationTest {

    @Autowired
    private KafkaTemplate<String, VendaCriadaMessage> kafkaTemplate;

    @Autowired
    private ConsumerFactory<String, VendaCriadaMessage> consumerFactory;

    @Test
    void devePublicarEVendaCriada() {
        VendaCriadaMessage message = new VendaCriadaMessage("v123", "c456", 100.0);
        kafkaTemplate.send("venda-criada", message);

        Consumer<String, VendaCriadaMessage> consumer = consumerFactory.createConsumer();
        consumer.subscribe(List.of("venda-criada"));

        ConsumerRecords<String, VendaCriadaMessage> records = KafkaTestUtils.getRecords(consumer, Duration.ofSeconds(5));
        assertFalse(records.isEmpty());
    }
}
