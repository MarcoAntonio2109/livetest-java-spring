package com.softdesign.livetest.api.cliente;

import com.softdesign.livetest.domain.cliente.ClienteNotFound;
import com.softdesign.livetest.domain.cliente.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController()
public class ClientesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientesController.class);

    private final ClienteService clienteService;

    public ClientesController(
            ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> listAll() {
        try {
            var clientes = clienteService.listAll();

            return ResponseEntity.accepted().body(ClienteDTO.from(clientes));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> get(@PathVariable("id") String id) {
        try {
            var cliente = clienteService.getById(id);

            return ResponseEntity.accepted().body(ClienteDTO.from(cliente));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable("id") String id, @RequestBody UpdateClienteRequest updateClienteRequest) {
        try {
            var cliente = clienteService.update(updateClienteRequest.toCliente(id));

            return ResponseEntity.accepted().body(ClienteDTO.from(cliente));
        } catch (ClienteNotFound clienteNotFound) {
            LOGGER.error(clienteNotFound.getMessage(), clienteNotFound);
            return ResponseEntity.notFound().build();
        }catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> create(@RequestBody CreateClienteRequest createClienteRequest) {
        try {
            var cliente = clienteService.create(createClienteRequest.toCliente());
            var createdUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.id()).toUri();
            return ResponseEntity.created(createdUri).body(ClienteDTO.from(cliente));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

}
