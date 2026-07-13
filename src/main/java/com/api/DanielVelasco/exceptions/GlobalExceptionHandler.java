package com.api.DanielVelasco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice//Primero decimos que es un Excepcion
// Evita repetir bloques try-catch en todos los Controllers y centraliza el manejo de excepciones
public class GlobalExceptionHandler {
    //La anotacion sirve para decir que si el método existe, lo ejecute.

//    @ExceptionHandler(ProductoNoEncontradoException.class)
//    public ResponseEntity<String> manejarProductoNoEncontrado(ProductoNoEncontradoException ex){
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(ex.getMessage());
//    } Para este caso este método lo guardo, porque con el aprendí que puedo tener excepciones personalizadas

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> RecursoNoEncontrado(ResourceNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarExcepcionesDelValid(MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errores);
    }
}
