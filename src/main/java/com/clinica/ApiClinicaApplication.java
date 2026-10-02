package com.clinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * PUNTO DE INICIO DEL PROYECTO SPRING BOOT.
 *
 * @SpringBootApplication hace, de forma resumida:
 * - configuracion automatica de Spring Boot,
 * - deteccion de componentes (@Controller, @Service, etc.),
 * - configuracion base de la aplicacion.
 */
@SpringBootApplication
public class ApiClinicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiClinicaApplication.class, args);
    }
}
