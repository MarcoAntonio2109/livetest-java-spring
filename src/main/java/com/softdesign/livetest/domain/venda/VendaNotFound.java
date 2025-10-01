package com.softdesign.livetest.domain.venda;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Venda not found")
public class VendaNotFound extends RuntimeException {

    public VendaNotFound() {
    }

    public VendaNotFound(String message) {
        super(message);
    }

    public VendaNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public VendaNotFound(Throwable cause) {
        super(cause);
    }

    public VendaNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
