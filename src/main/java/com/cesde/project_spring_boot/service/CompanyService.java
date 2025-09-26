package com.cesde.project_spring_boot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cesde.project_spring_boot.dto.CompanyDTO;
import com.cesde.project_spring_boot.dto.UserDTO;
import com.cesde.project_spring_boot.model.Company;
import com.cesde.project_spring_boot.model.User;
import com.cesde.project_spring_boot.repository.CompanyRepository;
import com.cesde.project_spring_boot.repository.UserRepository;





@Service
public class CompanyService {
	
	@Autowired
    private CompanyRepository companyRepository;
	
	@Autowired 
	private UserRepository userRepository;

	public List<CompanyDTO> getAllCompannies() {
        List<Company> companys = companyRepository.findAll();

        // Convertir de User a UserDTO usando Stream API
        return companys.stream()
                .map(CompanyDTO::fromEntity)
                .collect(Collectors.toList());
    }
	
	//obtener empresa por id
	public CompanyDTO getCompanyById(Long id) {
		
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()) {
            return CompanyDTO.fromEntity(companyOptional.get());
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
	
	 public CompanyDTO createCompany(CompanyDTO companyDTO) {
	        // Validar que el email no exista
	        if (companyRepository.existsByEmail(companyDTO.getEmail())) {
	            throw new RuntimeException("Ya existe una empresa con el email: " + companyDTO.getEmail());
	        }

	        // Convertir DTO a entidad y guardar
	        Company company = companyDTO.toEntity();
	        Company savedCompany = companyRepository.save(company);

	        return CompanyDTO.fromEntity(savedCompany);
	    }
	 
	 public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
	        Optional<Company> companyOptional = companyRepository.findById(id);

	        if (companyOptional.isPresent()) {
	            Company company = companyOptional.get();
            
	            // Verificar si el email cambió y si ya existe
	            if (!company.getEmail().equals(companyDTO.getEmail()) &&
	                    companyRepository.existsByEmail(companyDTO.getEmail())) {
	                throw new RuntimeException("Ya existe una empresa con el email: " + companyDTO.getEmail());
	            }

	            // Actualizar los campos
	            company.setName(companyDTO.getName());
	            company.setNit(companyDTO.getNit());
	            company.setEmail(companyDTO.getEmail());
 
	            Company updatedCompany = companyRepository.save(company);
	            return CompanyDTO.fromEntity(updatedCompany);
	        } else {
	            throw new RuntimeException("Empresa no encontrada con ID: " + id);
	        }
	 }
	 
	 public void deleteCompany(Long companyId) {
	        if (companyRepository.existsById(companyId)) {
	            companyRepository.deleteById(companyId);
	        } else {
	            throw new RuntimeException("Empresa no encontrada con ID: " + companyId);
	        }
	    }

	 public List<User> addEmployee( UserDTO employeeDTO, long companyId){
	 
		 Optional<Company> companyOptional = companyRepository.findById(companyId);
		 
		//verificar que la empresa exista
		 if (!companyOptional.isPresent()) {
			 throw new RuntimeException("Empresa no encontrada con ID: " + companyId);
		 }
		    
		 Company company = companyOptional.get();
		 
		 
		    User employee = employeeDTO.toEntity();
		    employee.setCompany(company);
		    
		    User savedEmployee = userRepository.save(employee);

	        //añadir empleado a la lista 
	        company.addEmployee(savedEmployee);

	        // Guardar la empresa
	        companyRepository.save(company);

	        // Retornar lista de empleados
	        return company.getEmployees();
	 }
	    
	 public void deleteEmployeeById(CompanyDTO companyDTO, long empleadoId) {
		 Optional<Company> companyOptional = companyRepository.findById(companyDTO.getId());
		 
		 //saber si empresa existe
		 if (!companyOptional.isPresent()) {
			 throw new RuntimeException("Empresa no encontrada con ID: " + companyDTO.getId());
		 }
		 
		 
		 User employeeToEliminate = (User) companyDTO.getEmployees().stream().filter(user -> user.getId() == empleadoId);
		 //Verificar si el empleado existe
		 if (employeeToEliminate == null) {
			 throw new RuntimeException("Usuario no encontrado con ID: " + empleadoId);
		 }
		 
		
		 
		Company company = companyOptional.get();
		company.removeEmployee(employeeToEliminate);
	 }
}
