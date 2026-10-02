package com.clinica.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * ENTIDAD JPA
 * Esta clase representa la tabla tb_medicamento en MySQL.
 *
 * A diferencia de MedicamentoDTO, esta clase pertenece a la capa de persistencia:
 * su responsabilidad principal es mapear Java <-> Base de datos.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_medicamento")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_med")
    private Integer codigo;

    @Column(name = "nom_med")
    private String nombre;

    @Column(name = "stock_med")
    private int stock;

    @Column(name = "pre_med")
    private double precio;

    @Column(name = "fec_ven_med")
    private LocalDate fechaVencimiento;

    /*
     * Muchos medicamentos pueden pertenecer a un mismo laboratorio.
     *
     * En la tabla tb_medicamento existe la FK cod_lab.
     * Esta es la parte "muchos" de la relacion Muchos-a-Uno.
     */
    @ManyToOne
    @JoinColumn(name = "cod_lab")
    private Laboratorio laboratorio;
}
