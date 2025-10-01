package com.softdesign.livetest.api.produto;

import com.softdesign.livetest.domain.produto.Produto;

public record UpdateProdutoRequest(String nome, String descricao, Double valor) {

    public Produto toProduto(String id) {
        return new Produto(id, this.nome(), this.descricao(), this.valor());
    }

}
