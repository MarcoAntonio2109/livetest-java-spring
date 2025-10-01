package com.softdesign.livetest.api.kafka;

import com.softdesign.livetest.api.dto.MensagemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        kafkaTemplate.send("test-topic", message);
        return ResponseEntity.ok("✅ Mensagem enviada: " + message);
    }

    @PostMapping("/send/json")
    public ResponseEntity<String> sendJson(@RequestBody MensagemDTO dto) {
        kafkaTemplate.send("test-topic", dto.getConteudo());
        return ResponseEntity.ok("✅ JSON enviado: " + dto.getConteudo());
    }
}
