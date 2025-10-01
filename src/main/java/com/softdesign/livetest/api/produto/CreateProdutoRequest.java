package com.softdesign.livetest.api.produto;

import com.softdesign.livetest.domain.produto.Produto;

import java.util.List;
import java.util.stream.Collectors;

public record CreateProdutoRequest(String nome, String descricao, Double valor) {

    public Produto toProduto() {
        return new Produto(null, this.nome(), this.descricao(), this.valor());
    }

}
