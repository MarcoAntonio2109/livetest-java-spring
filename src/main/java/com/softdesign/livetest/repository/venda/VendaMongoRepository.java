package com.softdesign.livetest.repository.venda;

import com.softdesign.livetest.domain.produto.Produto;
import com.softdesign.livetest.domain.venda.Venda;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VendaMongoRepository extends MongoRepository<Venda, String> {

    List<Venda> findByClienteId(String clienteId);

}
