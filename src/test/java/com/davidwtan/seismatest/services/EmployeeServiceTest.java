package com.davidwtan.seismatest.services;

import com.davidwtan.seismatest.models.Employee;
import com.davidwtan.seismatest.models.Payslip;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    @Test
    void baseTest() {
        var taxService = new TaxService();
        var employeeService = new EmployeeService(taxService);
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

    @Test
    void september30DaysInMonth() {
        var taxService = new TaxService();
        var employeeService = new EmployeeService(taxService);
        var employees = Collections.singletonList(
                new Employee("David", "Tan", 0, 8, 0)
        );
        var payslips = employeeService.generatePayslip(employees);
        testPaySlips(payslips,
                employees,
                Collections.singletonList("01 September"),
                Collections.singletonList("30 September"),
                Collections.singletonList(0),
                Collections.singletonList(0),
                Collections.singletonList(0),
                Collections.singletonList(0)
        );
    }

    @Test
    void thisFebruaryLeapYearTest() {
        var taxService = new TaxService();
        var employeeService = new EmployeeService(taxService);
        var employees = Collections.singletonList(
                new Employee("David", "Tan", 0, 1, 0)
        );
        var payslips = employeeService.generatePayslip(employees);

        var daysInFeb = LocalDate.now().isLeapYear() ? 29 : 28;
        testPaySlips(payslips,
                employees,
                Collections.singletonList("01 February"),
                Collections.singletonList(daysInFeb + " February"),
                Collections.singletonList(0),
                Collections.singletonList(0),
                Collections.singletonList(0),
                Collections.singletonList(0)
        );
    }

    @Test
    void noTax() {
        var taxService = new TaxService();
        var employeeService = new EmployeeService(taxService);
        var employees = Collections.singletonList(
                new Employee("David", "Tan", 17777, 0, 0.09)
        );
        var payslips = employeeService.generatePayslip(employees);
        testPaySlips(payslips,
                employees,
                Collections.singletonList("01 January"),
                Collections.singletonList("31 January"),
                Collections.singletonList(1481),
                Collections.singletonList(0),
                Collections.singletonList(133),
                Collections.singletonList(1481)
        );
    }

    @Test
    void secondTierTax() {
        var taxService = new TaxService();
        var employeeService = new EmployeeService(taxService);
        var employees = Collections.singletonList(
                new Employee("David", "Tan", 35000, 0, 0.09)
        );
        var payslips = employeeService.generatePayslip(employees);
        testPaySlips(payslips,
                employees,
                Collections.singletonList("01 January"),
                Collections.singletonList("31 January"),
                Collections.singletonList(2917),
                Collections.singletonList(266),
                Collections.singletonList(263),
                Collections.singletonList(2651)
        );
    }

    @Test
    void thirdTierTax() {
        var taxService = new TaxService();
        var employeeService = new EmployeeService(taxService);
        var employees = Collections.singletonList(
                new Employee("David", "Tan", 60050, 0, 0.09)
        );
        var payslips = employeeService.generatePayslip(employees);
        testPaySlips(payslips,
                employees,
                Collections.singletonList("01 January"),
                Collections.singletonList("31 January"),
                Collections.singletonList(5004),
                Collections.singletonList(922),
                Collections.singletonList(450),
                Collections.singletonList(4082)
        );
    }


    @Test
    void fourthTierTax() {
        var taxService = new TaxService();
        var employeeService = new EmployeeService(taxService);
        var employees = Collections.singletonList(
                new Employee("David", "Tan", 120000, 0, 0.09)
        );
        var payslips = employeeService.generatePayslip(employees);
        testPaySlips(payslips,
                employees,
                Collections.singletonList("01 January"),
                Collections.singletonList("31 January"),
                Collections.singletonList(10000),
                Collections.singletonList(2669),
                Collections.singletonList(900),
                Collections.singletonList(7331)
        );
    }

    @Test
    void fifthTierTax() {
        var taxService = new TaxService();
        var employeeService = new EmployeeService(taxService);
        var employees = Collections.singletonList(
                new Employee("David", "Tan", 200000, 0, 0.09)
        );
        var payslips = employeeService.generatePayslip(employees);
        testPaySlips(payslips,
                employees,
                Collections.singletonList("01 January"),
                Collections.singletonList("31 January"),
                Collections.singletonList(16667),
                Collections.singletonList(5269),
                Collections.singletonList(1500),
                Collections.singletonList(11398)
        );
    }

    @Test
    void multipleEmployees() {
        var taxService = new TaxService();
        var employeeService = new EmployeeService(taxService);
        var employees = Arrays.asList(
                new Employee("David", "Tan", 200000, 0, 0.09),
                new Employee("Winston", "Tan", 120000, 0, 0.09)

        );
        var payslips = employeeService.generatePayslip(employees);
        testPaySlips(payslips,
                employees,
                Arrays.asList("01 January", "01 January"),
                Arrays.asList("31 January", "31 January"),
                Arrays.asList(16667, 10000),
                Arrays.asList(5269, 2669),
                Arrays.asList(1500, 900),
                Arrays.asList(11398, 7331)
        );
    }

    // -----------------------   Helpers   -----------------------------
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
}