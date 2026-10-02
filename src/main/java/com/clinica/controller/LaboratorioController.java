package com.clinica.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.entity.Laboratorio;
import com.clinica.services.LaboratorioServices;

/*
 * CONTROLADOR REST DE LABORATORIOS.
 *
 * Esta parte conserva el estilo CRUD basico:
 * Controller -> Service generico -> Repository -> BD.
 *
 * Todavia no utiliza LaboratorioDTO, ModelMapper, @Valid, ApiResponse
 * ni ResponseEntity. Eso permite comparar este controlador con el de
 * Medicamento y entender que mejoras se fueron agregando despues.
 */
@RestController
@RequestMapping("/labo")
public class LaboratorioController {

    private final LaboratorioServices labServices;

    public LaboratorioController(LaboratorioServices labServices) {
        this.labServices = labServices;
    }

    @GetMapping("/lista")
    public List<Laboratorio> lista() throws Exception {
        return labServices.listarTodos();
    }

    @GetMapping("/buscar/{codigo}")
    public Laboratorio buscar(@PathVariable Integer codigo) throws Exception {
        return labServices.buscar(codigo);
    }

    @PostMapping("/registrar")
    public Laboratorio registrar(@RequestBody Laboratorio lab) throws Exception {
        return labServices.registrar(lab);
    }

    @PutMapping("/actualizar")
    public Laboratorio actualizar(@RequestBody Laboratorio lab) throws Exception {
        return labServices.actualizar(lab);
    }

    @DeleteMapping("/eliminar/{codigo}")
    public void eliminar(@PathVariable Integer codigo) throws Exception {
        labServices.eliminar(codigo);
    }
}
