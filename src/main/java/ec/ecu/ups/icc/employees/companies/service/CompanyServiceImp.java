package ec.ecu.ups.icc.employees.companies.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.ecu.ups.icc.employees.companies.dto.CompanyDepartmentsDto;
import ec.ecu.ups.icc.employees.companies.entity.CompanyEntity;
import ec.ecu.ups.icc.employees.companies.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ec.ecu.ups.icc.employees.departments.dto.DepartmentEmployeesDto;
import ec.ecu.ups.icc.employees.departments.entity.DepartmentEntity;
import ec.ecu.ups.icc.employees.departments.repository.DepartmentRepository;
import ec.ecu.ups.icc.employees.employees.dto.EmployeesResponseDto;
import ec.ecu.ups.icc.employees.employees.dto.EmployeeWithDepartmentDto;
import ec.ecu.ups.icc.employees.employees.entity.EmployeeEntity;
import ec.ecu.ups.icc.employees.employees.repository.EmployeeRepository;
import ec.ecu.ups.icc.employees.departments.dto.DepartmentSimpleDto;

@Service
public class CompanyServiceImp implements CompanyService {

    private static final String ACTIVE = "S";

    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyServiceImp(CompanyRepository companyRepository,
            DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDepartmentsDto getCompanyDepartments(Long companyId) {
        CompanyEntity company = companyRepository.findByIdAndActive(companyId, ACTIVE)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Company not found or inactive"
                ));

        List<DepartmentEntity> departments = departmentRepository.findByCompany_IdAndActive(company.getId(), ACTIVE);
        List<DepartmentEmployeesDto> departmentDtos = new ArrayList<>();

        double totalBudget = 0.0;

        for (DepartmentEntity department : departments) {
            List<EmployeeEntity> employees = employeeRepository.findByDepartment_IdAndActive(
                    department.getId(),
                    ACTIVE
            );
            departmentDtos.add(new DepartmentEmployeesDto(
                    department.getId(),
                    department.getName(),
                    department.getBudget(),
                    employees.size()
            ));
            if (department.getBudget() != null) {
                totalBudget += department.getBudget();
            }
        }

        return new CompanyDepartmentsDto(
                company.getId(),
                company.getName(),
                company.getCountry(),
                departmentDtos.size(),
                totalBudget,
                departmentDtos
        );
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeesResponseDto getHighSalaryEmployees(Long companyId, Double minSalary) {
        CompanyEntity company = companyRepository.findByIdAndActive(companyId, ACTIVE)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Company not found or inactive"
                ));

        double threshold = minSalary == null ? 5000.00 : minSalary;
        List<EmployeeEntity> employees = employeeRepository.findHighSalaryEmployeesByCompany(companyId, threshold);
        sortEmployeesBySalaryDesc(employees);
        List<EmployeeWithDepartmentDto> employeeDtos = new ArrayList<>();

        for (EmployeeEntity employee : employees) {
            DepartmentSimpleDto dept = new DepartmentSimpleDto(
                    employee.getDepartment().getId(),
                    employee.getDepartment().getName()
            );
            EmployeeWithDepartmentDto dto = new EmployeeWithDepartmentDto(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getSalary(),
                    dept
            );
            employeeDtos.add(dto);
        }

        return new EmployeesResponseDto(company.getName(), threshold, employeeDtos.size(), employeeDtos);
    }

    private void sortEmployeesBySalaryDesc(List<EmployeeEntity> employees) {
        int n = employees.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double salaryI = employees.get(i).getSalary() == null ? 0.0 : employees.get(i).getSalary();
                double salaryJ = employees.get(j).getSalary() == null ? 0.0 : employees.get(j).getSalary();
                if (salaryI < salaryJ) {
                    EmployeeEntity temp = employees.get(i);
                    employees.set(i, employees.get(j));
                    employees.set(j, temp);
                }
            }
        }
    }
}
