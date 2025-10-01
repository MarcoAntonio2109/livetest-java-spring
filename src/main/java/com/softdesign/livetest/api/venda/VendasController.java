package com.softdesign.livetest.api.venda;

import com.softdesign.livetest.applicationservice.venda.CreateVendaApplicationService;
import com.softdesign.livetest.domain.venda.VendaService;
import com.softdesign.livetest.domain.venda.adiciolnaritemvenda.AdicionarItemVendaMessage;
import com.softdesign.livetest.domain.venda.adiciolnaritemvenda.AdicionarItemVendaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class VendasController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendasController.class);

    private final VendaService vendaService;

    private final CreateVendaApplicationService createVendaApplicationService;

    private final AdicionarItemVendaProducer adicionarItemVendaProducer;


    public VendasController(
            VendaService vendaService,
            CreateVendaApplicationService createVendaApplicationService,
            AdicionarItemVendaProducer adicionarItemVendaProducer) {
        this.vendaService = vendaService;
        this.createVendaApplicationService = createVendaApplicationService;
        this.adicionarItemVendaProducer = adicionarItemVendaProducer;

    }

    @GetMapping("/vendas")
    public ResponseEntity<List<VendaDTO>> listAll() {
        try {
            var vendas = vendaService.listAll();

            return ResponseEntity.accepted().body(VendaDTO.from(vendas));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @GetMapping("/vendas/{id}")
    public ResponseEntity<VendaDTO> get(@PathVariable("id") String id) {
        try {
            var venda = vendaService.getById(id);

            return ResponseEntity.accepted().body(VendaDTO.from(venda));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @PostMapping("/vendas")
    public ResponseEntity<VendaDTO> createa(@RequestBody CreateVendaRequest createVendaRequest) {
        try {
            var venda = createVendaApplicationService.execute(createVendaRequest);
            var createdUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(venda.id()).toUri();
            return ResponseEntity.created(createdUri).body(VendaDTO.from(venda));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @PostMapping("/vendas/{id}/item")
    public ResponseEntity<Void> addItemVenda(@PathVariable("id") String id,
                                             @RequestBody AdicionarItemVendaRequest adicionarItemVendaRequest) {
        try {
            adicionarItemVendaProducer.send(new AdicionarItemVendaMessage(
                    id,
                    adicionarItemVendaRequest.produtoId(),
                    adicionarItemVendaRequest.quantidade()));
            return ResponseEntity.accepted().build();
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }
    @GetMapping("/vendas/cliente/{clienteId}")
    public ResponseEntity<List<VendaDTO>> listByCliente(@PathVariable String clienteId) {
        try {
            var vendas = vendaService.listByCliente(clienteId);
            return ResponseEntity.ok(VendaDTO.from(vendas));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

}
