package com.softdesign.livetest.api.cliente;

import com.softdesign.livetest.domain.cliente.Cliente;

public record CreateClienteRequest(String nome, Integer idade, EnderecoDTO enderecoDTO) {

    public Cliente toCliente() {
        return new Cliente(null, this.nome, this.idade, this.enderecoDTO.toEndereco());
    }

}
