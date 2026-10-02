package com.clinica.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * ENTIDAD que representa tb_laboratorio.
 * Tiene la parte "uno" de la relacion:
 * un Laboratorio puede tener muchos Medicamentos.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_laboratorio")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_lab")
    private Integer codigo;

    @Column(name = "des_lab")
    private String descripcion;

    /*
     * mappedBy = "laboratorio" significa:
     * la relacion se controla desde el atributo laboratorio de Medicamento.
     *
     * @JsonIgnore evita que al convertir Laboratorio a JSON se produzca
     * una navegacion circular del tipo:
     * Laboratorio -> Medicamentos -> Laboratorio -> Medicamentos -> ...
     */
    @JsonIgnore
    @OneToMany(mappedBy = "laboratorio")
    private List<Medicamento> listaMedicamentos;
}
