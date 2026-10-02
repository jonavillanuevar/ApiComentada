package com.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.entity.Laboratorio;

/*
 * Repository encargado del acceso a datos de Laboratorio.
 * No tiene metodos propios porque por ahora alcanza con los que ofrece
 * JpaRepository.
 */
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer> {
}
