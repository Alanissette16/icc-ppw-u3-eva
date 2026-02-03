package ec.ecu.ups.icc.employees.departments.dto;

public class DepartmentDto {

    private Long id;
    private String name;
    private Double budget;

    public DepartmentDto() {
    }

    public DepartmentDto(Long id, String name, Double budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
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
}
