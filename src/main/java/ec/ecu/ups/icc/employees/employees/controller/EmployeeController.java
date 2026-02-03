package ec.ecu.ups.icc.employees.employees.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.ecu.ups.icc.employees.employees.dto.EmployeeTransferRequestDto;
import ec.ecu.ups.icc.employees.employees.dto.EmployeeTransferResponseDto;
import ec.ecu.ups.icc.employees.employees.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PatchMapping("/{employeeId}/transfer")
    public EmployeeTransferResponseDto transferEmployee(
            @PathVariable("employeeId") Long employeeId,
            @Valid @RequestBody EmployeeTransferRequestDto request) {
        return employeeService.transferEmployee(employeeId, request.getNewDepartmentId());
    }
}
