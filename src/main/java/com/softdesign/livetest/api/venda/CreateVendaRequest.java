package com.softdesign.livetest.api.venda;

import com.softdesign.livetest.domain.venda.ItemVenda;

import java.util.List;

public record CreateVendaRequest(String clienteId, List<ItemVenda> listaItemVenda ){

}
