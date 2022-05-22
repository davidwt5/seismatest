package com.davidwtan.seismatest.services;

import com.davidwtan.seismatest.models.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    @Test
    void baseTest() {
        var employeeService = new EmployeeService();
        var employees = new ArrayList<Employee>();
        employees.add(new Employee("David", "Tan", 0, 0, 0));

        var paySlips = employeeService.generatePayslip(employees);
        for(int i=0; i<paySlips.size(); i++) {
            var paySlip = paySlips.get(i);
            assertEquals(paySlip.getEmployee(), employees.get(i));
            assertEquals(paySlip.getFromDate(), "01 January");
            assertEquals(paySlip.getToDate(), "31 January");
            assertEquals(paySlip.getGrossIncome(), 0);
            assertEquals(paySlip.getIncomeTax(), 0);
            assertEquals(paySlip.getSuperannuation(), 0);
            assertEquals(paySlip.getNetIncome(), 0);
        }
    }

    // PayPeriod tests
//    void january();
//    void september();
//    void thisFeburary();

}