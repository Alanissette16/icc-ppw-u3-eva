package ec.ecu.ups.icc.employees.departments.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.ecu.ups.icc.employees.departments.dto.DepartmentWithEmployeesDto;
import ec.ecu.ups.icc.employees.departments.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public DepartmentWithEmployeesDto getDepartmentEmployees(
            @PathVariable("id") Long id,
            @RequestParam(name = "sort", required = false, defaultValue = "desc") String sort) {
        return departmentService.getDepartmentEmployees(id, sort);
    }
}
