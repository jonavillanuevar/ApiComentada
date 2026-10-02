package com.clinica.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/*
 * MANEJADOR GLOBAL DE EXCEPCIONES.
 *
 * @RestControllerAdvice permite centralizar errores de todos los controladores.
 * Asi evitamos llenar cada endpoint de try/catch repetidos.
 *
 * Flujo general:
 * ocurre una excepcion -> Spring busca @ExceptionHandler compatible
 * -> este Handler crea ApiResponse -> se devuelve el codigo HTTP adecuado.
 */
@RestControllerAdvice
public class ValidationHandler {

    /*
     * Captura errores producidos por @Valid.
     * Ejemplo: nombre vacio o stock fuera del rango permitido.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationErrors(MethodArgumentNotValidException e) {

        Map<String, String> errores = new HashMap<>();

        // Recorremos cada campo invalido y guardamos: campo -> mensaje.
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        ApiResponse<Map<String, String>> response =
                new ApiResponse<>(false, "Errores de validación", errores);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /* Recurso buscado pero inexistente -> HTTP 404. */
    @ExceptionHandler(ModeloNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> manejarModeloNotFoundException(
            ModeloNotFoundException ex, WebRequest request) {

        ApiResponse<String> response =
                new ApiResponse<>(false, ex.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /*
     * Regla de negocio incumplida -> HTTP 409 Conflict.
     * Ejemplo: nombre de medicamento duplicado.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex) {

        ApiResponse<String> response =
                new ApiResponse<>(false, ex.getMessage(), null);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    /*
     * Ultima red de seguridad: cualquier Exception no tratada especificamente
     * termina aqui como HTTP 500 Internal Server Error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {

        ApiResponse<String> response =
                new ApiResponse<>(false, "Error interno del servidor", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
