package com.clinica.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.interfaces.ICRUD;
import com.clinica.utils.ModeloNotFoundException;

/*
 * IMPLEMENTACION GENERICA DEL CRUD.
 *
 * Esta clase contiene el codigo repetitivo que antes tendriamos que escribir
 * nuevamente en MedicamentoServices, LaboratorioServices, etc.
 *
 * T  = entidad
 * ID = tipo del id
 *
 * Es abstracta porque no sabe cual Repository concreto debe usar.
 * Cada Service hijo le entrega su repository implementando repo().
 */
public abstract class ICRUDImpl<T, ID> implements ICRUD<T, ID> {

    /*
     * "Contrato" que deben completar las clases hijas.
     * Ejemplo MedicamentoServices devuelve MedicamentoRepository.
     */
    public abstract JpaRepository<T, ID> repo();

    @Override
    public T registrar(T bean) throws Exception {
        // save() viene de Spring Data JPA.
        return repo().save(bean);
    }

    @Override
    public T actualizar(T bean) throws Exception {
        // save() tambien se usa para actualizar una entidad existente.
        return repo().save(bean);
    }

    @Override
    public void eliminar(ID cod) throws Exception {
        repo().deleteById(cod);
    }

    @Override
    public T buscar(ID cod) throws Exception {
        /*
         * findById devuelve Optional<T>.
         * Si existe, devuelve la entidad.
         * Si NO existe, lanzamos nuestra excepcion personalizada.
         * Esa excepcion sera capturada por ValidationHandler y convertida
         * en una respuesta HTTP 404.
         */
        return repo().findById(cod)
                .orElseThrow(() -> new ModeloNotFoundException("Código : " + cod + " no existe"));
    }

    @Override
    public List<T> listarTodos() throws Exception {
        return repo().findAll();
    }
}
