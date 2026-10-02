package com.clinica.interfaces;

import java.util.List;

/*
 * INTERFAZ GENERICA DEL CRUD.
 *
 * T  = tipo de entidad. Ejemplo: Medicamento o Laboratorio.
 * ID = tipo de su clave primaria. En este proyecto es Integer.
 *
 * Esta interfaz define QUE operaciones debe tener el CRUD,
 * pero todavia no explica COMO se realizan.
 */
public interface ICRUD<T, ID> {

    T registrar(T bean) throws Exception;

    T actualizar(T bean) throws Exception;

    void eliminar(ID cod) throws Exception;

    T buscar(ID cod) throws Exception;

    List<T> listarTodos() throws Exception;
}
