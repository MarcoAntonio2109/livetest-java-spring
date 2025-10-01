package com.softdesign.livetest.migration.mongo;

import com.softdesign.livetest.domain.cliente.Cliente;
import com.softdesign.livetest.domain.cliente.Endereco;
import com.softdesign.livetest.repository.cliente.ClienteMongoRepository;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;

@ChangeUnit(id = "0001", order = "1", author = "ricardo.camelo")
public class DatabaseChangeLog0001 {

    @Execution
    public void execution(ClienteMongoRepository clienteMongoRepository) {

        clienteMongoRepository.save(new Cliente(null,
                "Arnaldo Coelho",
                60,
                new Endereco("Rua Siqueira Campos",
                        "1184",
                        "sala 101")));

        clienteMongoRepository.save(new Cliente(null,
                "Calbi Peixoto",
                75,
                new Endereco("Rua Riachuelo",
                        "1304",
                        "apto 501")));

        clienteMongoRepository.save(new Cliente(null,
                "Roberto Carlos",
                80,
                new Endereco("Copacabana",
                        "2504",
                        "apto 1301")));
    }

    @RollbackExecution
    public void rollbackExecution(ClienteMongoRepository clienteMongoRepository) {
    }
}
