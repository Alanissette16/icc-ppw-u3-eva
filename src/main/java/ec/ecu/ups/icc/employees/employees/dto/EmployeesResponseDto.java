package ec.ecu.ups.icc.employees.employees.dto;

import java.util.ArrayList;
import java.util.List;

public class EmployeesResponseDto {

    private String companyName;
    private double minSalary;
    private long count;
    private List<EmployeeWithDepartmentDto> employees = new ArrayList<>();

    public EmployeesResponseDto() {
    }

    public EmployeesResponseDto(String companyName, double minSalary, long count,
            List<EmployeeWithDepartmentDto> employees) {
        this.companyName = companyName;
        this.minSalary = minSalary;
        this.count = count;
        this.employees = employees;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<EmployeeWithDepartmentDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeWithDepartmentDto> employees) {
        this.employees = employees;
    }
}
