package com.softdesign.livetest.api.venda;

import com.softdesign.livetest.api.produto.ProdutoDTO;
import com.softdesign.livetest.domain.produto.Produto;
import com.softdesign.livetest.domain.venda.ItemVenda;
import com.softdesign.livetest.domain.venda.Venda;

import java.util.List;
import java.util.stream.Collectors;

public record ItemVendaDTO(String produtoId, Integer quantidade, Double valorTotal) {

    public static ItemVendaDTO from(ItemVenda itemVenda) {
        return new ItemVendaDTO(itemVenda.produtoId(), itemVenda.quantidade(), itemVenda.valorTotal());
    }

    public static List<ItemVendaDTO> from(List<ItemVenda> itemsVenda) {
        return itemsVenda.stream().map(ItemVendaDTO::from).toList();
    }

}
