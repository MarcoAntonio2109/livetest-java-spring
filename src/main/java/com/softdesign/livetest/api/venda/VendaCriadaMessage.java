package com.softdesign.livetest.api.venda;

public record VendaCriadaMessage(
        String vendaId,
        String clienteId,
        Double valorTotal
) {}
