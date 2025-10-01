package com.softdesign.livetest.domain.cliente;

import org.springframework.data.annotation.Id;

public record Cliente(@Id String id, String nome, Integer idade, Endereco endereco) {

}
