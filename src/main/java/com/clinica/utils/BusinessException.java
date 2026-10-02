package com.clinica.utils;

/*
 * Excepcion para reglas de NEGOCIO.
 *
 * No significa necesariamente que el programa este mal.
 * Significa que la operacion solicitada no cumple una regla definida.
 * Ejemplo actual: intentar registrar un nombre de medicamento repetido.
 *
 * ValidationHandler la transforma en HTTP 409 Conflict.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessException(String mensaje) {
        super(mensaje);
    }
}
