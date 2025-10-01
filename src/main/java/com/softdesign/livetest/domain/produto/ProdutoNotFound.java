package com.softdesign.livetest.domain.produto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Produto not found")
public class ProdutoNotFound extends RuntimeException {

    public ProdutoNotFound() {
    }

    public ProdutoNotFound(String message) {
        super(message);
    }

    public ProdutoNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ProdutoNotFound(Throwable cause) {
        super(cause);
    }

    public ProdutoNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
