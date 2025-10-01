package com.softdesign.livetest.domain.venda;

public record ItemVenda(String produtoId, Integer quantidade, Double valorTotal) {

    public ItemVenda withQuantidade(Integer quantidade) {
        return new ItemVenda(this.produtoId, quantidade, this.valorTotal);
    }

    public ItemVenda withValorTotal(Double valorTotal) {
        return new ItemVenda(this.produtoId, this.quantidade, valorTotal);
    }

}
