package com.softdesign.livetest.redis;

import com.softdesign.livetest.domain.venda.Venda;
import com.softdesign.livetest.domain.venda.VendaService;
import com.softdesign.livetest.repository.venda.VendaMongoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RedisCacheTest {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private VendaMongoRepository vendaMongoRepository;

    @Test
    void deveCachearVendaPorId() {
        Venda venda = new Venda("v123", "c456", 50.0, List.of());
        vendaMongoRepository.save(venda);

        Venda primeiraChamada = vendaService.getById("v123"); // consulta e cacheia
        Venda segundaChamada = vendaService.getById("v123"); // vem do cache

        assertEquals(primeiraChamada, segundaChamada);
    }
}
