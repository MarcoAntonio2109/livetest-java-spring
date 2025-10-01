package com.softdesign.livetest.domain.cliente;

import com.softdesign.livetest.repository.cliente.ClienteMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteMongoRepository clienteMongoRepository;


    public ClienteService(ClienteMongoRepository clienteMongoRepository) {
        this.clienteMongoRepository = clienteMongoRepository;

    }


    public Cliente create(Cliente cliente) {
        return clienteMongoRepository.insert(cliente);
    }

    public Cliente update(Cliente cliente) {
        var optionalCliente = clienteMongoRepository.findById(cliente.id());

        if (optionalCliente.isEmpty()) {
            throw new ClienteNotFound("cliente não encontrado - " + cliente.id());
        }

        return clienteMongoRepository.save(cliente);
    }

    @Cacheable(value = "clientes", key = "#clientId")
    public Cliente getById(String clientId) {
        var optionalCliente = clienteMongoRepository.findById(clientId);

        return optionalCliente.orElseThrow(() ->
                new ClienteNotFound("cliente não encontrado - " + clientId));
    }

    @Cacheable(value = "clientes-lista")
    public List<Cliente> listAll() {
        return clienteMongoRepository.findAll();
    }

}
