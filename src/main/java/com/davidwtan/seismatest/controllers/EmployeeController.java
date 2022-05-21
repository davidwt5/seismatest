package com.davidwtan.seismatest.controllers;

import com.davidwtan.seismatest.models.Employee;
import com.davidwtan.seismatest.models.Payslip;
import com.davidwtan.seismatest.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/payslip")
    public List<Payslip> payslip(@RequestBody List<Employee> employees) {
        return employeeService.generatePayslip(employees);
    }
}
