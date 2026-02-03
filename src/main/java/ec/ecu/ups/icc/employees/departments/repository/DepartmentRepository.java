package ec.ecu.ups.icc.employees.departments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.ecu.ups.icc.employees.departments.entity.DepartmentEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
    Optional<DepartmentEntity> findByIdAndActive(Long id, String active);

    List<DepartmentEntity> findByCompany_IdAndActive(Long companyId, String active);
}
