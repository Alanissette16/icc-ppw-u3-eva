package ec.ecu.ups.icc.employees.companies.dto;

import java.util.ArrayList;
import java.util.List;

import ec.ecu.ups.icc.employees.departments.dto.DepartmentEmployeesDto;

public class CompanyDepartmentsDto {

    private Long companyId;
    private String companyName;
    private String country;
    private long departmentCount;
    private double totalBudget;
    private List<DepartmentEmployeesDto> departments = new ArrayList<>();

    public CompanyDepartmentsDto() {
    }

    public CompanyDepartmentsDto(Long companyId, String companyName, String country, long departmentCount,
            double totalBudget, List<DepartmentEmployeesDto> departments) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.country = country;
        this.departmentCount = departmentCount;
        this.totalBudget = totalBudget;
        this.departments = departments;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getDepartmentCount() {
        return departmentCount;
    }

    public void setDepartmentCount(long departmentCount) {
        this.departmentCount = departmentCount;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public List<DepartmentEmployeesDto> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentEmployeesDto> departments) {
        this.departments = departments;
    }
}
