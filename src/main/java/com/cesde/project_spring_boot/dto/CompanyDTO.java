package com.cesde.project_spring_boot.dto;
import java.util.ArrayList;
import java.util.List;
import com.cesde.project_spring_boot.model.Company;
import com.cesde.project_spring_boot.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CompanyDTO {
	private Long id;

	  @NotBlank(message = "name is mandatory")
	  private String name;

	  @NotBlank(message = "nit is mandatory")
	  private String nit;

	  @NotBlank(message = "Email is mandatory")
	  @Email(message = "Email should be valid")
	  private String email;

	  private List<User> employees = new ArrayList<>();
	  
	  public CompanyDTO() {}

	  public CompanyDTO(Long id, String name, String nit, String email, List<User> employees) {
		    this.id = id;
		    this.name = name;
		    this.nit = nit;
		    this.email = email;
		    this.employees = employees;
		  }
	  
	  public static CompanyDTO fromEntity(Company company) {
		    return new CompanyDTO(
		      company.getId(),
		      company.getName(),
		      company.getNit(),
		      company.getEmail(),
		      company.getEmployees()
		    );   
		    }
	  
	  
		    public Company toEntity() {
		        Company company = new Company();
		        company.setId(this.id);
		        company.setName(this.name);
		        company.setNit(this.nit);
		        company.setEmail(this.email);
		        company.setEmployees(this.employees);
		        return company;
		      }
		  
		    public void updateEntity(Company company) {
		        company.setName(this.name);
		        company.setNit(this.nit);
		        company.setEmail(this.email);
		        company.setEmployees(this.employees);
		      }

			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getNit() {
				return nit;
			}

			public void setNit(String nit) {
				this.nit = nit;
			}

			public String getEmail() {
				return email;
			}

			public void setEmail(String email) {
				this.email = email;
			}

			public List<User> getEmployees() {
				return employees;
			}

			
		
}	  
