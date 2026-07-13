package com.api.DanielVelasco.exceptions;
//Creamos una clase directamente para personalizar la respuesta de la excepcion, para cada excepcion una clase diferente
public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
