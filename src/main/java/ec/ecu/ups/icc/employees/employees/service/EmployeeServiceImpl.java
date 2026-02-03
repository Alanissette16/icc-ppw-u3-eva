package ec.ecu.ups.icc.employees.employees.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ec.ecu.ups.icc.employees.departments.dto.DepartmentSimpleDto;
import ec.ecu.ups.icc.employees.departments.entity.DepartmentEntity;
import ec.ecu.ups.icc.employees.departments.repository.DepartmentRepository;
import ec.ecu.ups.icc.employees.employees.dto.EmployeeTransferResponseDto;
import ec.ecu.ups.icc.employees.employees.entity.EmployeeEntity;
import ec.ecu.ups.icc.employees.employees.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String ACTIVE = "S";

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public EmployeeTransferResponseDto transferEmployee(Long employeeId, Long newDepartmentId) {
        EmployeeEntity employee = employeeRepository.findByIdAndActive(employeeId, ACTIVE)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employee not found or inactive"
                ));

        DepartmentEntity oldDepartment = employee.getDepartment();

        DepartmentEntity targetDepartment = departmentRepository.findByIdAndActive(newDepartmentId, ACTIVE)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Target department not found or inactive"
                ));

        Long currentDepartmentId = oldDepartment != null ? oldDepartment.getId() : null;
        if (currentDepartmentId != null && currentDepartmentId.equals(targetDepartment.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Employee is already in the target department"
            );
        }

        employee.setDepartment(targetDepartment);
        employeeRepository.save(employee);

        return new EmployeeTransferResponseDto(
                employee.getId(),
                employee.getFirstName() + " " + employee.getLastName(),
                oldDepartment == null ? null : new DepartmentSimpleDto(oldDepartment.getId(), oldDepartment.getName()),
                new DepartmentSimpleDto(targetDepartment.getId(), targetDepartment.getName()),
                "Employee transferred successfully"
        );
    }
}
