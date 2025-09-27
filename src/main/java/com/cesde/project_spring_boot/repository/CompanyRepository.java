package com.cesde.project_spring_boot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cesde.project_spring_boot.model.Company;
import com.cesde.project_spring_boot.model.User;

@Repository 
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	//Saber si una empresa existe por el id
	
	
	
	//Mirar los empleados que pertenecen a una empresa por el id
	@Query("SELECT u FROM User u WHERE u.company.id = :companyId")
	List<User> EmployeesOfACompany(@Param ("CompanyId") Long companyId);

	boolean existsByEmail(String email);

	//Saber si empresa existe por NIT
	
}
