package com.davidwtan.seismatest.controllers;

import com.davidwtan.seismatest.models.Employee;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @PostMapping("/testinput")
    public List<Employee> testinput(@RequestBody List<Employee> employees) {
        System.out.println(employees);
        return employees;
    }
}
