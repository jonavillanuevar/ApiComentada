package com.clinica.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.clinica.entity.Medicamento;
import com.clinica.repository.MedicamentoRepository;
import com.clinica.utils.BusinessException;

/*
 * SERVICE DE MEDICAMENTO.
 *
 * Hereda el CRUD comun de ICRUDImpl, pero aqui se agregan reglas
 * especificas del negocio de medicamentos.
 */
@Service
public class MedicamentoServices extends ICRUDImpl<Medicamento, Integer> {

    private final MedicamentoRepository repo;

    public MedicamentoServices(MedicamentoRepository repo) {
        this.repo = repo;
    }

    /*
     * Con este metodo le decimos a ICRUDImpl cual Repository debe utilizar.
     * Aunque el retorno esta declarado como JpaRepository, realmente
     * estamos devolviendo MedicamentoRepository, que hereda de JpaRepository.
     */
    @Override
    public JpaRepository<Medicamento, Integer> repo() {
        return repo;
    }

    /*
     * Este metodo agrega una REGLA DE NEGOCIO antes de guardar:
     * no se permite registrar dos medicamentos con el mismo nombre.
     *
     * Flujo:
     * Controller -> grabar(m)
     *            -> existsByNombre(...)
     *            -> si existe: BusinessException -> HTTP 409
     *            -> si no existe: registrar(m) -> save() -> BD
     */
    public Medicamento grabar(Medicamento m) throws Exception {

        if (repo.existsByNombre(m.getNombre())) {
            throw new BusinessException("Nombre de medicamento existe");
        }

        return registrar(m);
    }
}
