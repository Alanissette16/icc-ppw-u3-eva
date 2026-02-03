package ec.ecu.ups.icc.employees.companies.mapper;

import ec.ecu.ups.icc.employees.companies.dto.CompanyDto;
import ec.ecu.ups.icc.employees.companies.entity.CompanyEntity;

public final class CompanyMapper {

    private CompanyMapper() {
    }

    public static CompanyDto toDto(CompanyEntity entity) {
        if (entity == null) {
            return null;
        }
        return new CompanyDto(entity.getId(), entity.getName(), entity.getCountry());
    }
}
