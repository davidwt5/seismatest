package com.davidwtan.seismatest.services;

import com.davidwtan.seismatest.interfaces.PayslipGenerator;
import com.davidwtan.seismatest.models.*;
import com.davidwtan.seismatest.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements PayslipGenerator {
    private final TaxService taxService;

    @Autowired
    EmployeeService(TaxService taxService) {
        this.taxService = taxService;
    }

    @Override
    public List<Payslip> generatePayslip(List<Employee> employees) {
        return employees.stream().map(employee -> {
                    var payPeriod = calcPayPeriod(employee.getPaymentMonth());
                    var grossIncome = calcGrossIncome(employee.getAnnualSalary());
                    var incomeTax = calcIncomeTax(employee.getAnnualSalary());
                    return new Payslip(
                            employee,
                            payPeriod.getFromDate(),
                            payPeriod.getToDate(),
                            grossIncome,
                            incomeTax,
                            calcSuperannuation(grossIncome, employee.getSuperRate()),
                            calcNetIncome(grossIncome, incomeTax)
                    );
                }
        ).collect(Collectors.toList());
    }

    // https://stackoverflow.com/questions/28177370/how-to-format-localdate-to-string
    // https://stackoverflow.com/questions/13624442/getting-last-day-of-the-month-in-a-given-string-date
    // Assumption: paymentMonth = 0 = January, 2 = February, ..., 11 = December
    private PayPeriod calcPayPeriod(int paymentMonth) {
        var thisYear = LocalDate.now().getYear();
        // +1 on the month argument because it takes [1,12] instead of [0,11] from the argument
        var startOfMonth = LocalDate.of(thisYear, paymentMonth+1, 1);
        var endOfMonth = startOfMonth.withDayOfMonth(
                startOfMonth.getMonth().length(startOfMonth.isLeapYear()));
        var formatter = DateTimeFormatter.ofPattern("dd LLLL");
        return new PayPeriod(
                startOfMonth.format(formatter),
                endOfMonth.format(formatter));
    }

    private int calcGrossIncome(int annualSalary) {
        return Utilities.roundDecimal((double) annualSalary / 12);
    }

    // Monthly Income Tax; calculated using the annual income tax table divided by 12
    private int calcIncomeTax(int annualSalary) {
        return taxService.calcIncomeTax(annualSalary);
    }

    private int calcNetIncome(int grossIncome, int incomeTax) {
        return grossIncome - incomeTax;
    }

    private int calcSuperannuation(int grossIncome, double superRate) {
        return Utilities.roundDecimal(grossIncome * superRate);
    }
}
