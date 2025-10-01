package com.softdesign.livetest.domain.venda;

import org.springframework.data.annotation.Id;

import java.util.List;

public record Venda (@Id String id, String clienteId, Double valorTotal, List<ItemVenda> items) {

    public Venda withValorTotal (Double valorTotal) {
        return new Venda(this.id, this.clienteId, valorTotal, this.items);
    }

}
