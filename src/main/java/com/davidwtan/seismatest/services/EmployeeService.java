package com.davidwtan.seismatest.services;

import com.davidwtan.seismatest.interfaces.PayslipGenerator;
import com.davidwtan.seismatest.models.Employee;
import com.davidwtan.seismatest.models.Payslip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements PayslipGenerator {
    @Override
    public List<Payslip> generatePayslip(List<Employee> employees) {
        System.out.println(employees);
        return null;
    }
}
