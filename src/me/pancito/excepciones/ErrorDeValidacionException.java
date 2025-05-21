package me.pancito.excepciones;

public class ErrorDeValidacionException extends Exception {
    public ErrorDeValidacionException(String mensaje) {
        super(mensaje);
    }
}
