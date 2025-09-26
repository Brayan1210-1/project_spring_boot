package com.cesde.project_spring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cesde.project_spring_boot.dto.CompanyDTO;
import com.cesde.project_spring_boot.dto.UserDTO;
import com.cesde.project_spring_boot.model.User;
import com.cesde.project_spring_boot.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/companys")
@Tag(name = "👥 Gestión de empresas", description = "API para crear, leer, actualizar y eliminar empresas"
		+ "(incluidos los empleados)")

public class CompanyController {

	 @Autowired
	    private CompanyService companyService;
	 
	 @GetMapping
	    @Operation(summary = "Obtener todas las empresas",
	            description = "Retorna la lista completa de empresas sin paginación")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
	            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
	    })
	    public ResponseEntity<List<CompanyDTO>> getAllCompanys() {
	        try {
	            List<CompanyDTO> companys = companyService.getAllCompannies();
	            return ResponseEntity.ok(companys);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	 
	 //obtener empresa por id
	 @GetMapping("/{id}")
	    @Operation(summary = "Obtener una empresa por ID",
	            description = "Retorna una empresa específica usando su ID único")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Empresa encontrado"),
	            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
	    })
	    public ResponseEntity<CompanyDTO> getCompanyById(
	            @Parameter(description = "ID único de la empresa", required = true, example = "1")
	            @PathVariable Long id) {

	        try {
	            CompanyDTO company = companyService.getCompanyById(id);
	            return ResponseEntity.ok(company);
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	 //Crear empresa
	 @PostMapping
	    @Operation(summary = "Crear una nueva empresa",
	            description = "Crea una empresa con la información proporcionada")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "201", description = "Empresa creada exitosamente"),
	            @ApiResponse(responseCode = "400", description = "Datos inválidos, email ya existe o nit ya existe"),
	    })
	    public ResponseEntity<CompanyDTO> createCompany(
	            @io.swagger.v3.oas.annotations.parameters.RequestBody(
	                    description = "Información de la nueva empresa", required = true)
	            @Valid @RequestBody CompanyDTO companyDTO) {

	        try {
	            CompanyDTO createdCompany = companyService.createCompany(companyDTO);
	            return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().build();
	        }
	    }
	 
	 @PutMapping("/{id}")
	    @Operation(summary = "Actualizar una empresa completa",
	            description = "Actualiza toda la información de la empresa (menos la lista de empleados)")
	    public ResponseEntity<CompanyDTO> updateCompany(
	            @Parameter(description = "ID de la empresa", required = true, example = "1")
	            @PathVariable Long id,

	            @Valid @RequestBody CompanyDTO companyDTO) {

	        try {
	            CompanyDTO updatedCompany = companyService.updateCompany(id, companyDTO);
	            return ResponseEntity.status(HttpStatus.OK).body(updatedCompany);
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	 //Eliminar empresa por id
	 @DeleteMapping("/{id}")
	    @Operation(summary = "Eliminar empresa por id",
	            description = "Elimina una empresa por su identificador")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "204", description = "Empresa eliminada exitosamente"),
	            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
	    })
	    public ResponseEntity<Void> deleteCompany(
	            @Parameter(description = "ID de la empresa", required = true, example = "1")
	            @PathVariable Long id) {

	        try {
	            companyService.deleteCompany(id);
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
	 
	 //crear un empleado
	 @PostMapping("/{id}/employees")
	 @Operation(summary = "crear un empleado",
	           description = "Se proporciona el id de una empresa y se crea un empleado en ella ")
	 @ApiResponses(value = {
	          @ApiResponse(responseCode = "201", description = "Empleado creado"),
	          @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
	 })
	 public ResponseEntity<User> addUser(
			 @Parameter(description = "ID de la empresa", required = true, example = "1")
			 @PathVariable Long companyId, UserDTO employee){
		 
		 try {
			 companyService.addEmployee(employee, companyId);
			 return ResponseEntity.status(HttpStatus.CREATED).build();
	 } catch (RuntimeException e){
		 return ResponseEntity.notFound().build();
	 }
}
	 
}
