package com.softdesign.livetest.api;

import com.softdesign.livetest.domain.cliente.Cliente;
import com.softdesign.livetest.domain.cliente.ClienteNotFound;
import com.softdesign.livetest.domain.cliente.ClienteService;
import com.softdesign.livetest.domain.cliente.Endereco;
import com.softdesign.livetest.repository.cliente.ClienteMongoRepository;
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
class ClienteServiceTest {

    @Mock
    private ClienteMongoRepository clienteMongoRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = criarClientePadrao();
    }

    private Cliente criarClientePadrao() {
        Endereco endereco = new Endereco("Rua das Flores", "123", "Apto 45");
        return new Cliente("cliente123", "João", 30, endereco);
    }
    @Test
    void deveCriarCliente() {
        when(clienteMongoRepository.insert(cliente)).thenReturn(cliente);

        Cliente result = clienteService.create(cliente);

        assertEquals(cliente, result);
        verify(clienteMongoRepository).insert(cliente);
    }

    @Test
    void deveAtualizarClienteExistente() {
        when(clienteMongoRepository.findById("cliente123")).thenReturn(Optional.of(cliente));
        when(clienteMongoRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.update(cliente);

        assertEquals(cliente, result);
        verify(clienteMongoRepository).save(cliente);
    }

    @Test
    void deveLancarExcecaoAoAtualizarClienteInexistente() {
        when(clienteMongoRepository.findById("cliente123")).thenReturn(Optional.empty());

        assertThrows(ClienteNotFound.class, () -> clienteService.update(cliente));
    }

    @Test
    void deveBuscarClientePorId() {
        when(clienteMongoRepository.findById("cliente123")).thenReturn(Optional.of(cliente));

        Cliente result = clienteService.getById("cliente123");

        assertEquals(cliente, result);
        verify(clienteMongoRepository).findById("cliente123");
    }

    @Test
    void deveLancarExcecaoAoBuscarClienteInexistente() {
        when(clienteMongoRepository.findById("cliente123")).thenReturn(Optional.empty());

        assertThrows(ClienteNotFound.class, () -> clienteService.getById("cliente123"));
    }

    @Test
    void deveListarTodosOsClientes() {
        List<Cliente> clientes = List.of(cliente);
        when(clienteMongoRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.listAll();

        assertEquals(clientes, result);
        verify(clienteMongoRepository).findAll();
    }
}
