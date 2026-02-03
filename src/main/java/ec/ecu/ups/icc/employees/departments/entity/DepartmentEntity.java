package ec.ecu.ups.icc.employees.departments.entity;

import java.util.ArrayList;
import java.util.List;

import ec.ecu.ups.icc.employees.companies.entity.CompanyEntity;
import ec.ecu.ups.icc.employees.employees.entity.EmployeeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class DepartmentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name", nullable = false, length = 100)
    private String name;
    @Column(name="budget", nullable = false)
    private Double budget;
    @Column(name = "active",nullable = false, length = 1)
    private String active;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmployeeEntity> employees = new ArrayList<>();

    public DepartmentEntity() {
    }

    public DepartmentEntity(Long id, String name, Double budget, String active, CompanyEntity company,
            List<EmployeeEntity> employees) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.active = active;
        this.company = company;
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
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }
    public CompanyEntity getCompany() {
        return company;
    }
    public void setCompany(CompanyEntity company) {
        this.company = company;
    }
    public List<EmployeeEntity> getEmployees() {
        return employees;
    }
    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }
  
}
