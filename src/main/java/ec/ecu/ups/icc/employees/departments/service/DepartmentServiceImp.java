package ec.ecu.ups.icc.employees.departments.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.ecu.ups.icc.employees.companies.mapper.CompanyMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ec.ecu.ups.icc.employees.departments.dto.DepartmentWithEmployeesDto;
import ec.ecu.ups.icc.employees.departments.entity.DepartmentEntity;
import ec.ecu.ups.icc.employees.departments.repository.DepartmentRepository;
import ec.ecu.ups.icc.employees.employees.dto.EmployeeDto;
import ec.ecu.ups.icc.employees.employees.entity.EmployeeEntity;
import ec.ecu.ups.icc.employees.employees.mapper.EmployeeMapper;
import ec.ecu.ups.icc.employees.employees.repository.EmployeeRepository;

@Service
public class DepartmentServiceImp implements DepartmentService {

    private static final String ACTIVE = "S";

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentServiceImp(DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentWithEmployeesDto getDepartmentEmployees(Long departmentId, String sort) {
        DepartmentEntity department = departmentRepository.findByIdAndActive(departmentId, ACTIVE)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Department not found or inactive"
                ));

        List<EmployeeEntity> employees = employeeRepository.findByDepartment_IdAndActive(
                department.getId(),
                ACTIVE
        );
        boolean asc = "asc".equalsIgnoreCase(sort);
        sortEmployeesBySalary(employees, asc);
        List<EmployeeDto> employeeDtos = new java.util.ArrayList<>();
        double totalSalaries = 0.0;

        for (EmployeeEntity employee : employees) {
            employeeDtos.add(EmployeeMapper.toDto(employee));
            if (employee.getSalary() != null) {
                totalSalaries += employee.getSalary();
            }
        }

        return new DepartmentWithEmployeesDto(
                department.getId(),
                department.getName(),
                department.getBudget(),
                CompanyMapper.toDto(department.getCompany()),
                employeeDtos.size(),
                totalSalaries,
                employeeDtos
        );
    }

    private void sortEmployeesBySalary(List<EmployeeEntity> employees, boolean asc) {
        int n = employees.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double salaryI = employees.get(i).getSalary() == null ? 0.0 : employees.get(i).getSalary();
                double salaryJ = employees.get(j).getSalary() == null ? 0.0 : employees.get(j).getSalary();
                if (asc) {
                    if (salaryI > salaryJ) {
                        EmployeeEntity temp = employees.get(i);
                        employees.set(i, employees.get(j));
                        employees.set(j, temp);
                    }
                } else {
                    if (salaryI < salaryJ) {
                        EmployeeEntity temp = employees.get(i);
                        employees.set(i, employees.get(j));
                        employees.set(j, temp);
                    }
                }
            }
        }
    }
}
