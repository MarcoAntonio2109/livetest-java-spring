package com.softdesign.livetest.applicationservice.venda;

import com.softdesign.livetest.api.venda.CreateVendaRequest;
import com.softdesign.livetest.domain.cliente.ClienteService;
import com.softdesign.livetest.domain.produto.ProdutoService;
import com.softdesign.livetest.domain.venda.ItemVenda;
import com.softdesign.livetest.domain.venda.Venda;
import com.softdesign.livetest.domain.venda.VendaService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CreateVendaApplicationService {

    private final VendaService vendaService;

    private final ClienteService clienteService;

    private final ProdutoService produtoService;

    public CreateVendaApplicationService(VendaService vendaService, ClienteService clienteService, ProdutoService produtoService) {
        this.vendaService = vendaService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

//    public Venda execute(String clientId) {
//
//        var cliente = clienteService.getById(clientId);
//
//        var venda = new Venda(null, cliente.id(), 0.0d, Collections.emptyList());
//
//        return vendaService.create(venda);
//    }

    public Venda execute(CreateVendaRequest createVendaRequest) {
        var cliente = clienteService.getById(createVendaRequest.clienteId());

        List<ItemVenda> itens = createVendaRequest.listaItemVenda().stream()
                .map(itemRequest -> {
                    var produto = produtoService.getById(itemRequest.produtoId());
                    double valorTotal = produto.valor() * itemRequest.quantidade();
                    return new ItemVenda(produto.id(), itemRequest.quantidade(), valorTotal);
                })
                .toList();

        double valorTotalVenda = itens.stream()
                .mapToDouble(ItemVenda::valorTotal)
                .sum();

        var venda = new Venda(null, cliente.id(), valorTotalVenda, itens);

        return vendaService.create(venda);
    }

}
