package ec.ecu.ups.icc.employees.employees.service;

import ec.ecu.ups.icc.employees.employees.dto.EmployeeTransferResponseDto;

public interface EmployeeService {

    EmployeeTransferResponseDto transferEmployee(Long employeeId, Long newDepartmentId);
}
