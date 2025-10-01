package com.softdesign.livetest;

import com.softdesign.livetest.repository.cliente.ClienteMongoRepository;
import com.softdesign.livetest.repository.produto.ProdutoMongoRepository;
import com.softdesign.livetest.repository.venda.VendaMongoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;


@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class SoftdesignlivetestApplicationTests {

	@InjectMocks
	private ClienteMongoRepository clienteMongoRepository;

	@InjectMocks
	private ProdutoMongoRepository produtoMongoRepository;

	@InjectMocks
	private VendaMongoRepository vendaMongoRepository;

	@Test
	void contextLoads() {
	}

}
