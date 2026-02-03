package ec.ecu.ups.icc.employees.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.ecu.ups.icc.employees.employees.entity.EmployeeEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{
    Optional<EmployeeEntity> findByIdAndActive(Long id, String active);

    List<EmployeeEntity> findByDepartment_IdAndActive(Long departmentId, String active);

    @Query("""
        select e
        from EmployeeEntity e
        join e.department d
        join d.company c
        where c.id = :companyId
          and c.active = 'S'
          and d.active = 'S'
          and e.active = 'S'
          and e.salary >= :minSalary
    """)
    List<EmployeeEntity> findHighSalaryEmployeesByCompany(@Param("companyId") Long companyId,
            @Param("minSalary") Double minSalary);
}
