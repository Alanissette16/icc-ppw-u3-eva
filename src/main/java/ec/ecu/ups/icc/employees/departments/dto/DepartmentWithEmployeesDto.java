package ec.ecu.ups.icc.employees.departments.dto;

import java.util.ArrayList;
import java.util.List;

import ec.ecu.ups.icc.employees.companies.dto.CompanyDto;
import ec.ecu.ups.icc.employees.employees.dto.EmployeeDto;

public class DepartmentWithEmployeesDto {

    private Long id;
    private String name;
    private Double budget;
    private CompanyDto company;
    private long employeeCount;
    private double totalSalaries;
    private List<EmployeeDto> employees = new ArrayList<>();

    public DepartmentWithEmployeesDto() {
    }

    public DepartmentWithEmployeesDto(Long id, String name, Double budget, CompanyDto company, long employeeCount,
            double totalSalaries, List<EmployeeDto> employees) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.company = company;
        this.employeeCount = employeeCount;
        this.totalSalaries = totalSalaries;
        this.employees = employees;
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

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public double getTotalSalaries() {
        return totalSalaries;
    }

    public void setTotalSalaries(double totalSalaries) {
        this.totalSalaries = totalSalaries;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }
}
