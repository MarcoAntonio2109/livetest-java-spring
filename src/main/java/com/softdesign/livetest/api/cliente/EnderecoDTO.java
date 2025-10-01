package com.softdesign.livetest.api.cliente;

import com.softdesign.livetest.domain.cliente.Endereco;

public record EnderecoDTO(String rua, String numero, String complemento) {

    public Endereco toEndereco() {
        return new Endereco(this.rua, this.numero, this.complemento);
    }

    public static EnderecoDTO from(Endereco endereco) {
        return new EnderecoDTO(endereco.rua(), endereco.numero(), endereco.complemento());
    }
}
