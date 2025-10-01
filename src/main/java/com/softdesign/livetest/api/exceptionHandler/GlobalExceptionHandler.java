package com.softdesign.livetest.api.exceptionHandler;

import com.softdesign.livetest.domain.cliente.ClienteNotFound;
import com.softdesign.livetest.domain.venda.VendaNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNotFound.class)
    public ResponseEntity<ErrorResponse> handleClienteNotFound(ClienteNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("CLIENTE_NAO_ENCONTRADO", ex.getMessage()));
    }

    @ExceptionHandler(VendaNotFound.class)
    public ResponseEntity<ErrorResponse> handleVendaNotFound(VendaNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("VENDA_NAO_ENCONTRADA", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("ERRO_INTERNO", "Ocorreu um erro inesperado."));
    }
}
