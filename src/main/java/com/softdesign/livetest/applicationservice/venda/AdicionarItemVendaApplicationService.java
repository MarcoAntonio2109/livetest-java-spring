package com.softdesign.livetest.applicationservice.venda;

import com.softdesign.livetest.domain.produto.ProdutoService;
import com.softdesign.livetest.domain.venda.ItemVenda;
import com.softdesign.livetest.domain.venda.VendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AdicionarItemVendaApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdicionarItemVendaApplicationService.class);

    private final VendaService vendaService;

    private final ProdutoService produtoService;

    public AdicionarItemVendaApplicationService(VendaService vendaService, ProdutoService produtoService) {
        this.vendaService = vendaService;
        this.produtoService = produtoService;
    }

    public void executar(String vendId, String produtoId, Integer quantidade) {

        LOGGER.info("AdicionarItemVendaApplicationService.executar - [{}] [{}] [{}]", vendId, produtoId, quantidade);

        var venda = vendaService.getById(vendId);

        var produto = this.produtoService.getById(produtoId);

        var itemOptional = venda.items().stream().filter(
                itemVenda -> itemVenda.produtoId().equals(produto.id())).findFirst();

        if (itemOptional.isPresent()) {
            var item = itemOptional.get();
            var itemModificado = item.withValorTotal(item.valorTotal() + (quantidade * produto.valor()))
                    .withQuantidade(item.quantidade() + quantidade);
            venda.items().set(venda.items().indexOf(item), itemModificado);
        } else {
            venda.items().add(new ItemVenda(produto.id(), quantidade, quantidade * produto.valor()));
        }

        var valorTotalVenda = venda.items().stream().mapToDouble(ItemVenda::valorTotal).sum();

        vendaService.update(venda.withValorTotal(valorTotalVenda));
    }

}
