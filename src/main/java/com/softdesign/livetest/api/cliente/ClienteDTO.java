package com.softdesign.livetest.api.cliente;

import com.softdesign.livetest.domain.cliente.Cliente;

import java.util.List;

public record ClienteDTO(String id, String nome, Integer idade, EnderecoDTO enderecoDTO) {

    public Cliente toCliente() {
        return new Cliente(this.id, this.nome, this.idade, this.enderecoDTO.toEndereco());
    }

    public static ClienteDTO from(Cliente cliente) {
        return new ClienteDTO(cliente.id(), cliente.nome(), cliente.idade(), EnderecoDTO.from(cliente.endereco()));
    }

    public static List<ClienteDTO> from(List<Cliente> clientes) {
        return clientes.stream().map(ClienteDTO::from).toList();
    }

}
