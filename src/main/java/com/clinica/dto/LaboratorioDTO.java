package com.clinica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * DTO sencillo de Laboratorio.
 * Por ahora se usa principalmente como parte de MedicamentoDTO.
 *
 * OJO DIDACTICO:
 * LaboratorioController todavia devuelve la entidad Laboratorio directamente.
 * Eso permite comparar el CRUD basico con el enfoque DTO usado en Medicamento.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LaboratorioDTO {
    private Integer codigo;
    private String descripcion;
}
