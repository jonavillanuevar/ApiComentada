package com.clinica.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * FORMATO ESTANDAR DE RESPUESTA DE LA API.
 *
 * En vez de devolver solamente el objeto, la API puede responder siempre con:
 * {
 *   "success": true/false,
 *   "mensaje": "...",
 *   "data": ...
 * }
 *
 * T permite que data sea cualquier tipo:
 * MedicamentoDTO, List<MedicamentoDTO>, Map de errores, String, etc.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String mensaje;
    private T data;
}
