package ec.ecu.ups.icc.employees.companies.entity;

import java.util.ArrayList;
import java.util.List;

import ec.ecu.ups.icc.employees.departments.entity.DepartmentEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name", nullable = false, length = 100)
    private String name;
    @Column(name="country", nullable = false, length = 50)
    private String country;
    @Column(name = "active",nullable = false, length = 1)
    private String active;
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DepartmentEntity> departments = new ArrayList<>();

    public CompanyEntity() {
    }

    public CompanyEntity(Long id, String name, String country, String active, List<DepartmentEntity> departments) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.active = active;
        this.departments = departments;
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
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }
    public List<DepartmentEntity> getDepartments() {
        return departments;
    }
    public void setDepartments(List<DepartmentEntity> departments) {
        this.departments = departments;
    }
   
}
