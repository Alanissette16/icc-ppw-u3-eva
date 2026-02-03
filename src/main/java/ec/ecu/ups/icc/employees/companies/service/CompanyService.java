package ec.ecu.ups.icc.employees.companies.service;

import ec.ecu.ups.icc.employees.companies.dto.CompanyDepartmentsDto;
import ec.ecu.ups.icc.employees.employees.dto.EmployeesResponseDto;

public interface CompanyService {

    CompanyDepartmentsDto getCompanyDepartments(Long companyId);
    EmployeesResponseDto getHighSalaryEmployees(Long companyId, Double minSalary);
}
