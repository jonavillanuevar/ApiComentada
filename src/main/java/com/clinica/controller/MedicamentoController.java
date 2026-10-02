package com.clinica.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.dto.MedicamentoDTO;
import com.clinica.entity.Medicamento;
import com.clinica.services.MedicamentoServices;
import com.clinica.utils.ApiResponse;

import jakarta.validation.Valid;

/*
 * CONTROLADOR REST DE MEDICAMENTOS.
 * Ruta base: /med
 *
 * Aqui ya se ve una version mas completa que LaboratorioController:
 * - usa DTO
 * - usa ModelMapper
 * - valida datos con @Valid
 * - devuelve ResponseEntity
 * - usa un formato comun ApiResponse
 */
@RestController
@RequestMapping("/med")
public class MedicamentoController {

    private final MedicamentoServices medServices;
    private final ModelMapper mapper;

    /*
     * Inyeccion por constructor.
     * Spring entrega automaticamente ambos objetos porque:
     * - MedicamentoServices tiene @Service
     * - ModelMapper fue declarado como @Bean en MapperConfig
     */
    public MedicamentoController(MedicamentoServices medServices, ModelMapper mapper) {
        this.medServices = medServices;
        this.mapper = mapper;
    }

    @GetMapping("/lista")
    public ResponseEntity<ApiResponse<?>> lista() throws Exception {

        /*
         * 1. Service obtiene List<Medicamento> de la BD.
         * 2. Cada entidad se transforma a MedicamentoDTO con ModelMapper.
         * 3. Se empaqueta todo dentro de ApiResponse.
         * 4. Se responde HTTP 200 OK.
         *
         * Aqui aparece Stream porque el profesor lo usa para transformar
         * una lista completa. Conceptualmente piensa en:
         * "por cada medicamento, convertirlo a DTO".
         */
        List<MedicamentoDTO> lista = medServices.listarTodos()
                .stream()
                .map(m -> mapper.map(m, MedicamentoDTO.class))
                .collect(Collectors.toList());

        ApiResponse<List<MedicamentoDTO>> response =
                new ApiResponse<>(true, "Listado de medicamentos", lista);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/buscar/{codigo}")
    public ResponseEntity<ApiResponse<?>> buscar(@PathVariable Integer codigo) throws Exception {

        // Si no existe, el Service lanza ModeloNotFoundException.
        Medicamento m = medServices.buscar(codigo);

        // La entidad encontrada se transforma a DTO antes de responder.
        MedicamentoDTO bean = mapper.map(m, MedicamentoDTO.class);

        ApiResponse<MedicamentoDTO> response =
                new ApiResponse<>(true, "Medicamento existe", bean);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<ApiResponse<?>> registrar(
            @Valid @RequestBody MedicamentoDTO med) throws Exception {

        /*
         * @RequestBody: convierte el JSON recibido a MedicamentoDTO.
         * @Valid: ejecuta @NotBlank, @Size, @Min, @Max del DTO.
         *
         * Si falla una validacion, el metodo ni siquiera continua:
         * Spring lanza MethodArgumentNotValidException y ValidationHandler
         * prepara el HTTP 400 con los errores.
         */

        // DTO -> Entidad, porque Repository/JPA trabaja con la entidad.
        Medicamento m = mapper.map(med, Medicamento.class);

        // Service valida la regla de negocio "nombre no repetido" y guarda.
        m = medServices.grabar(m);

        // Entidad guardada -> DTO para no devolver directamente la entidad.
        MedicamentoDTO bean = mapper.map(m, MedicamentoDTO.class);

        ApiResponse<MedicamentoDTO> response =
                new ApiResponse<>(true, "Medicamento registrado", bean);

        // 201 Created es apropiado cuando se crea un nuevo recurso.
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*
     * IMPORTANTE PARA TU APRENDIZAJE:
     * Estos dos metodos todavia estan en la version anterior del profesor.
     * Aun reciben/devuelven entidad directamente y no usan ApiResponse/DTO.
     * No los cambiamos para conservar exactamente el avance que estas estudiando.
     */
    @PutMapping("/actualizar")
    public Medicamento actualizar(@RequestBody Medicamento med) throws Exception {
        return medServices.actualizar(med);
    }

    @DeleteMapping("/eliminar/{codigo}")
    public void eliminar(@PathVariable Integer codigo) throws Exception {
        medServices.eliminar(codigo);
    }
}
