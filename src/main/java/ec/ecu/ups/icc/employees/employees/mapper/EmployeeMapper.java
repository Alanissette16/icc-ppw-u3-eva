package ec.ecu.ups.icc.employees.employees.mapper;

import ec.ecu.ups.icc.employees.employees.dto.EmployeeDto;
import ec.ecu.ups.icc.employees.employees.entity.EmployeeEntity;

public final class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static EmployeeDto toDto(EmployeeEntity entity) {
        if (entity == null) {
            return null;
        }
        return new EmployeeDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getSalary()
        );
    }
}
