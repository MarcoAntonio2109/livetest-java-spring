package com.softdesign.livetest.api.venda;

import com.softdesign.livetest.domain.venda.Venda;

import java.util.List;

public record VendaDTO(String id, String clienteId, Double valorTotal, List<ItemVendaDTO> items) {

    public static VendaDTO from(Venda venda) {
        return new VendaDTO(venda.id(), venda.clienteId(), venda.valorTotal(), ItemVendaDTO.from(venda.items()));
    }

    public static List<VendaDTO> from(List<Venda> vendas) {
        return vendas.stream().map(VendaDTO::from).toList();
    }

}
