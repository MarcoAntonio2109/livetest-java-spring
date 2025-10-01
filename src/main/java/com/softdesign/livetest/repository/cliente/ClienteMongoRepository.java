package com.softdesign.livetest.repository.cliente;

import com.softdesign.livetest.domain.cliente.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteMongoRepository extends MongoRepository<Cliente, String> {

}
