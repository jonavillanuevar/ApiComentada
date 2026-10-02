package com.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.entity.Medicamento;

/*
 * JpaRepository ya proporciona save, findAll, findById, deleteById, etc.
 *
 * existsByNombre NO necesita SQL escrito manualmente.
 * Spring Data JPA interpreta el nombre del metodo y genera la consulta.
 */
public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

    boolean existsByNombre(String nom);
}
