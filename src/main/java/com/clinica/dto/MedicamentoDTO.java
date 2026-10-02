package com.clinica.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * DTO = Data Transfer Object.
 *
 * Este objeto representa los datos que la API recibe o devuelve al cliente.
 * No es una entidad JPA y no representa directamente una tabla.
 *
 * Flujo al registrar:
 * JSON -> MedicamentoDTO -> ModelMapper -> Medicamento -> Service -> Repository -> BD
 *
 * Flujo al responder:
 * BD -> Medicamento -> ModelMapper -> MedicamentoDTO -> JSON
 *
 * Una ventaja importante es que las validaciones de entrada pueden estar aqui
 * sin llenar la entidad de responsabilidades relacionadas con la API.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoDTO {

    private Integer codigo;

    // No acepta null, "" ni texto formado solo por espacios.
    // El mensaje se encuentra en validaciones.properties.
    @NotBlank(message = "{nombre.blank}")

    // El nombre debe tener al menos 3 caracteres.
    @Size(min = 3, message = "{nombre.min}")

    // El nombre puede tener como maximo 30 caracteres.
    @Size(max = 30, message = "{nombre.max}")
    private String nombre;

    // Regla de validacion: stock permitido entre 1 y 10.
    @Min(value = 1, message = "Campo stock mínimo 1")
    @Max(value = 10, message = "Campo stock máximo 10")
    private int stock;

    private double precio;
    private LocalDate fechaVencimiento;

    /*
     * En lugar de exponer directamente la entidad Laboratorio,
     * se usa su DTO. ModelMapper tambien puede mapear este objeto anidado
     * porque los nombres de los atributos coinciden.
     */
    private LaboratorioDTO laboratorio;
}
