package com.softdesign.livetest.api;

import com.softdesign.livetest.domain.venda.Venda;
import com.softdesign.livetest.domain.venda.VendaNotFound;
import com.softdesign.livetest.domain.venda.VendaService;
import com.softdesign.livetest.repository.venda.VendaMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VendaServiceTest {

    @Mock
    private VendaMongoRepository vendaMongoRepository;

    @InjectMocks
    private VendaService vendaService;

    private Venda venda;

    @BeforeEach
    void setUp() {
        venda = new Venda("venda123", "cliente456", 100.0, List.of());
    }

    @Test
    void deveCriarVenda() {
        when(vendaMongoRepository.insert(venda)).thenReturn(venda);

        Venda result = vendaService.create(venda);

        assertEquals(venda, result);
        verify(vendaMongoRepository).insert(venda);
    }

    @Test
    void deveAtualizarVendaExistente() {
        when(vendaMongoRepository.findById("venda123")).thenReturn(Optional.of(venda));
        when(vendaMongoRepository.save(venda)).thenReturn(venda);

        Venda result = vendaService.update(venda);

        assertEquals(venda, result);
        verify(vendaMongoRepository).save(venda);
    }

    @Test
    void deveLancarExcecaoAoAtualizarVendaInexistente() {
        when(vendaMongoRepository.findById("venda123")).thenReturn(Optional.empty());

        assertThrows(VendaNotFound.class, () -> vendaService.update(venda));
    }

    @Test
    void deveBuscarVendaPorId() {
        when(vendaMongoRepository.findById("venda123")).thenReturn(Optional.of(venda));

        Venda result = vendaService.getById("venda123");

        assertEquals(venda, result);
        verify(vendaMongoRepository).findById("venda123");
    }

    @Test
    void deveLancarExcecaoAoBuscarVendaInexistente() {
        when(vendaMongoRepository.findById("venda123")).thenReturn(Optional.empty());

        assertThrows(VendaNotFound.class, () -> vendaService.getById("venda123"));
    }

    @Test
    void deveListarTodasAsVendas() {
        List<Venda> vendas = List.of(venda);
        when(vendaMongoRepository.findAll()).thenReturn(vendas);

        List<Venda> result = vendaService.listAll();

        assertEquals(vendas, result);
        verify(vendaMongoRepository).findAll();
    }

    @Test
    void deveListarVendasPorCliente() {
        List<Venda> vendas = List.of(venda);
        when(vendaMongoRepository.findByClienteId("cliente456")).thenReturn(vendas);

        List<Venda> result = vendaService.listByCliente("cliente456");

        assertEquals(vendas, result);
        verify(vendaMongoRepository).findByClienteId("cliente456");
    }
}
