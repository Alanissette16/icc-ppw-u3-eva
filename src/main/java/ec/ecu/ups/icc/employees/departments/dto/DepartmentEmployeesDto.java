package ec.ecu.ups.icc.employees.departments.dto;

public class DepartmentEmployeesDto {

    private Long id;
    private String name;
    private Double budget;
    private long employeeCount;

    public DepartmentEmployeesDto() {
    }

    public DepartmentEmployeesDto(Long id, String name, Double budget, long employeeCount) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.employeeCount = employeeCount;
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

    public long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }
}
