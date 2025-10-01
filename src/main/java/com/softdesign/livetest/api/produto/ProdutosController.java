package com.softdesign.livetest.api.produto;

import com.softdesign.livetest.domain.cliente.ClienteNotFound;
import com.softdesign.livetest.domain.produto.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ProdutosController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutosController.class);

    private final ProdutoService produtoService;

    public ProdutosController(
            ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoDTO>> listAll() {
        try {
            var produtos = produtoService.listAll();

            return ResponseEntity.accepted().body(ProdutoDTO.from(produtos));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoDTO> get(@PathVariable("id") String id) {
        try {
            var produto = produtoService.getById(id);

            return ResponseEntity.accepted().body(ProdutoDTO.from(produto));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable("id") String id, @RequestBody UpdateProdutoRequest updateProdutoRequest) {
        try {
            var produto = produtoService.update(updateProdutoRequest.toProduto(id));

            return ResponseEntity.accepted().body(ProdutoDTO.from(produto));
        } catch (ClienteNotFound clienteNotFound) {
            LOGGER.error(clienteNotFound.getMessage(), clienteNotFound);
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

    @PostMapping("/produtos")
    public ResponseEntity<ProdutoDTO> create(@RequestBody CreateProdutoRequest createProdutoRequest) throws URISyntaxException {
        try {
            var produto = produtoService.create(createProdutoRequest.toProduto());
            var createdUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.id()).toUri();
            return ResponseEntity.created(createdUri).body(ProdutoDTO.from(produto));
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }
    }

}
