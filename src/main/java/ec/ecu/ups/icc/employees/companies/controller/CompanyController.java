package ec.ecu.ups.icc.employees.companies.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.ecu.ups.icc.employees.companies.dto.CompanyDepartmentsDto;
import ec.ecu.ups.icc.employees.companies.service.CompanyService;
import ec.ecu.ups.icc.employees.employees.dto.EmployeesResponseDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}/departments")
    public CompanyDepartmentsDto getCompanyDepartments(@PathVariable("id") Long id) {
        return companyService.getCompanyDepartments(id);
    }

    @GetMapping("/{id}/high-salary-employees")
    public EmployeesResponseDto getHighSalaryEmployees(
            @PathVariable("id") Long id,
            @RequestParam(name = "minSalary", required = false) Double minSalary) {
        return companyService.getHighSalaryEmployees(id, minSalary);
    }
}
