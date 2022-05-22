package com.davidwtan.seismatest.services;

import com.davidwtan.seismatest.models.Employee;
import com.davidwtan.seismatest.models.Payslip;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    void testPaySlips(List<Payslip> payslips,
                      List<Employee> employees,
                      List<String> expectedFromDates,
                      List<String> expectedToDates,
                      List<Integer> expectedGrossIncomes,
                      List<Integer> expectedIncomeTaxes,
                      List<Integer> expectedSuperannuations,
                      List<Integer> expectedNetIncomes) {
        for(int i=0; i<payslips.size(); i++) {
            var paySlip = payslips.get(i);
            assertEquals(paySlip.getEmployee(), employees.get(i));
            assertEquals(paySlip.getFromDate(), expectedFromDates.get(i));
            assertEquals(paySlip.getToDate(), expectedToDates.get(i));
            assertEquals(paySlip.getGrossIncome(), expectedGrossIncomes.get(i));
            assertEquals(paySlip.getIncomeTax(), expectedIncomeTaxes.get(i));
            assertEquals(paySlip.getSuperannuation(), expectedSuperannuations.get(i));
            assertEquals(paySlip.getNetIncome(), expectedNetIncomes.get(i));
        }
    }

    @Test
    void baseTest() {
        var employeeService = new EmployeeService();
        var employees = Collections.singletonList(
                new Employee("David", "Tan", 0, 0, 0)
        );
        var payslips = employeeService.generatePayslip(employees);
        testPaySlips(payslips,
                employees,
                Collections.singletonList("01 January"),
                Collections.singletonList("31 January"),
                Collections.singletonList(0),
                Collections.singletonList(0),
                Collections.singletonList(0),
                Collections.singletonList(0)
        );
    }

    // PayPeriod tests
//    void january();
//    void september();
//    void thisFeburary();

}