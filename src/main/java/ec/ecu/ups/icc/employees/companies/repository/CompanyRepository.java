package ec.ecu.ups.icc.employees.companies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.ecu.ups.icc.employees.companies.entity.CompanyEntity;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    Optional<CompanyEntity> findByIdAndActive(Long id, String active);
}
