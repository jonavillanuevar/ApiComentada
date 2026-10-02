package com.clinica.utils;

/*
 * Excepcion personalizada para indicar que un recurso no existe.
 * Ejemplo: buscar medicamento codigo 999 inexistente.
 * ValidationHandler la convierte en HTTP 404 Not Found.
 */
public class ModeloNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ModeloNotFoundException(String mensaje) {
        super(mensaje);
    }
}
