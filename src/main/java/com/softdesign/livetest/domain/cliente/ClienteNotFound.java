package com.softdesign.livetest.domain.cliente;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Customer not found")
public class ClienteNotFound extends RuntimeException {

    public ClienteNotFound() {
    }

    public ClienteNotFound(String message) {
        super(message);
    }

    public ClienteNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ClienteNotFound(Throwable cause) {
        super(cause);
    }

    public ClienteNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
