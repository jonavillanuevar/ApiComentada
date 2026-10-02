package com.clinica.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.clinica.entity.Laboratorio;
import com.clinica.repository.LaboratorioRepository;

/*
 * SERVICE DE LABORATORIO.
 *
 * Este Service por ahora no tiene reglas de negocio propias.
 * Simplemente reutiliza el CRUD generico heredado de ICRUDImpl.
 */
@Service
public class LaboratorioServices extends ICRUDImpl<Laboratorio, Integer> {

    private final LaboratorioRepository repo;

    public LaboratorioServices(LaboratorioRepository repo) {
        this.repo = repo;
    }

    @Override
    public JpaRepository<Laboratorio, Integer> repo() {
        return repo;
    }
}
