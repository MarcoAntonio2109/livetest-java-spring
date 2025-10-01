package com.softdesign.livetest.repository.produto;

import com.softdesign.livetest.domain.produto.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoMongoRepository extends MongoRepository<Produto, String> {
}
