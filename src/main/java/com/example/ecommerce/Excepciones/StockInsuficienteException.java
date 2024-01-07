package com.example.ecommerce.Excepciones;

public class StockInsuficienteException extends RuntimeException{
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
