package com.softdesign.livetest.domain.cliente;

import com.softdesign.livetest.api.cliente.EnderecoDTO;

public record Endereco(String rua, String numero, String complemento) {

    public EnderecoDTO toEnderecoDTO() {
        return new EnderecoDTO(this.rua, this.numero, this.complemento);
    }
}
