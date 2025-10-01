package com.softdesign.livetest.domain.produto;

import org.springframework.data.annotation.Id;

public record Produto(@Id String id, String nome, String descricao, Double valor) {
}
