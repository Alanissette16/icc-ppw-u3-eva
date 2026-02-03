package ec.ecu.ups.icc.employees.departments.service;

import ec.ecu.ups.icc.employees.departments.dto.DepartmentWithEmployeesDto;

public interface DepartmentService {

    DepartmentWithEmployeesDto getDepartmentEmployees(Long departmentId, String sort);
}
