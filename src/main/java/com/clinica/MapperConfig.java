package com.clinica;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * CONFIGURACION DE MODELMAPPER
 *
 * ModelMapper sirve para convertir objetos que tienen datos parecidos.
 * En este proyecto se usa principalmente para convertir:
 *
 * MedicamentoDTO -> Medicamento
 * Medicamento    -> MedicamentoDTO
 *
 * La idea es evitar escribir manualmente muchos setNombre(), setStock(), etc.
 *
 * @Configuration indica que esta clase contiene configuracion de Spring.
 */
@Configuration
public class MapperConfig {

    /*
     * @Bean hace que Spring cree y administre UN objeto ModelMapper.
     * Luego puede inyectarlo donde se necesite, por ejemplo en
     * MedicamentoController mediante su constructor.
     */
    @Bean
    public ModelMapper model() {
        return new ModelMapper();
    }
}
