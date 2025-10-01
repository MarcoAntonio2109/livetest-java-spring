package com.softdesign.livetest.mongo;

import com.softdesign.livetest.domain.venda.Venda;
import com.softdesign.livetest.repository.venda.VendaMongoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class VendaMongoRepositoryTest {

    @Autowired
    private VendaMongoRepository vendaMongoRepository;

    @Test
    void deveSalvarEVenda() {
        Venda venda = new Venda(null, "cliente123", 80.0, List.of());
        Venda salva = vendaMongoRepository.save(venda);

        assertNotNull(salva.id());
        assertEquals("cliente123", salva.clienteId());
    }

    @Test
    void deveBuscarPorClienteId() {
        Venda venda = new Venda(null, "cliente456", 120.0, List.of());
        vendaMongoRepository.save(venda);

        List<Venda> vendas = vendaMongoRepository.findByClienteId("cliente456");
        assertFalse(vendas.isEmpty());
    }
}