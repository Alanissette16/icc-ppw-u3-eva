package ec.ecu.ups.icc.employees.departments.mapper;

import ec.ecu.ups.icc.employees.departments.dto.DepartmentDto;
import ec.ecu.ups.icc.employees.departments.entity.DepartmentEntity;

public final class DepartmentMapper {

    private DepartmentMapper() {
    }

    public static DepartmentDto toDto(DepartmentEntity entity) {
        if (entity == null) {
            return null;
        }
        return new DepartmentDto(entity.getId(), entity.getName(), entity.getBudget());
    }
}
