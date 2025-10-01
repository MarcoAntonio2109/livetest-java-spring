package com.softdesign.livetest.domain.produto;

import com.softdesign.livetest.domain.venda.VendaNotFound;
import com.softdesign.livetest.repository.produto.ProdutoMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProdutoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoMongoRepository produtoMongoRepository;

    public ProdutoService(ProdutoMongoRepository produtoMongoRepository) {
        this.produtoMongoRepository = produtoMongoRepository;
    }

    public Produto create(Produto produto) {
        return produtoMongoRepository.insert(produto);
    }

    public Produto update(Produto produto) {
        var optionalProduto = produtoMongoRepository.findById(produto.id());

        if (optionalProduto.isEmpty()) {
            throw new VendaNotFound("cliente não encontrado - " + produto.id());
        }

        return produtoMongoRepository.save(produto);
    }

    public Produto getById(String produtoId) {
        var optionalProduto = produtoMongoRepository.findById(produtoId);

        return optionalProduto.orElseThrow(() ->
                new VendaNotFound("produto não encontrado - " + produtoId));
    }

    public List<Produto> listAll() {
        var produtos = produtoMongoRepository.findAll();

        return produtos;
    }

}
