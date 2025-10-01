package com.softdesign.livetest.domain.venda;

import com.softdesign.livetest.domain.venda.adiciolnaritemvenda.AdicionarItemVendaMessage;
import com.softdesign.livetest.domain.venda.adiciolnaritemvenda.AdicionarItemVendaProducer;
import com.softdesign.livetest.repository.venda.VendaMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VendaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendaService.class);

    private final VendaMongoRepository vendaMongoRepository;



    public VendaService(VendaMongoRepository vendaMongoRepository) {
        this.vendaMongoRepository = vendaMongoRepository;
    }

    public Venda create(Venda venda) {
        return vendaMongoRepository.insert(venda);
    }

    @CachePut(value = "vendas", key = "#venda.id")
    public Venda update(Venda venda) {
        var optionalProduto = vendaMongoRepository.findById(venda.id());

        if (optionalProduto.isEmpty()) {
            throw new VendaNotFound("venda não encontrada - " + venda.id());
        }

        return vendaMongoRepository.save(venda);
    }
    @Cacheable(value = "vendas", key = "#vendaId")
    public Venda getById(String vendaId) {
        var optionalvenda = vendaMongoRepository.findById(vendaId);

        return optionalvenda.orElseThrow(() ->
                new VendaNotFound("venda não encontrada - " + vendaId));
    }
    @Cacheable(value = "vendas-lista")
    public List<Venda> listAll() {
        var vendas = vendaMongoRepository.findAll();

        return vendas;
    }

    @Cacheable(value = "vendaCliente", key = "#clienteId")
    public List<Venda> listByCliente(String clienteId) {
        return vendaMongoRepository.findByClienteId(clienteId);
    }


}
