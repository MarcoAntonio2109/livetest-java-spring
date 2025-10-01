package com.softdesign.livetest.api.produto;

import com.softdesign.livetest.api.cliente.ClienteDTO;
import com.softdesign.livetest.domain.cliente.Cliente;
import com.softdesign.livetest.domain.produto.Produto;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.stream.Collectors;

public record ProdutoDTO(String id, String nome, String descricao, Double valor) {


    public Produto toProduto() {
        return new Produto(this.id(), this.nome(), this.descricao(), this.valor());
    }

    public static ProdutoDTO from(Produto produto) {
        return new ProdutoDTO(produto.id(), produto.nome(), produto.descricao(), produto.valor());
    }

    public static List<ProdutoDTO> from(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDTO::from).toList();
    }

}
