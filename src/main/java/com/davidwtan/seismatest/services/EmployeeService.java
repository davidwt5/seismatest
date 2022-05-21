package com.davidwtan.seismatest.services;

import com.davidwtan.seismatest.interfaces.PayslipGenerator;
import com.davidwtan.seismatest.models.Employee;
import com.davidwtan.seismatest.models.PayPeriod;
import com.davidwtan.seismatest.models.Payslip;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjuster;
import java.util.List;

@Service
public class EmployeeService implements PayslipGenerator {
    @Override
    public List<Payslip> generatePayslip(List<Employee> employees) {
        return null;
    }

    // https://stackoverflow.com/questions/28177370/how-to-format-localdate-to-string
    // https://stackoverflow.com/questions/13624442/getting-last-day-of-the-month-in-a-given-string-date
    // Assumption: paymentMonth = 0 = January, 2 = February, ..., 11 = December
    private PayPeriod calcPayPeriod(int paymentMonth) {
        var thisYear = LocalDate.now().getYear();
        var startOfMonth = LocalDate.of(thisYear, paymentMonth, 1);
        var endOfMonth = startOfMonth.withDayOfMonth(
                startOfMonth.getMonth().length(startOfMonth.isLeapYear()));
        var formatter = DateTimeFormatter.ofPattern("dd LLLL");
        return new PayPeriod(
                startOfMonth.format(formatter),
                endOfMonth.format(formatter));
    }

    private int grossIncome(int annualSalary) {
        return -1;
    }

    // Don't forget to round
    private int calcIncomeTax() {
        return -1;
    }

    // Don't forget to round
    private int calcNetIncome() {
        return -1;
    }

    // Don't forget to round
    private int getSuperannuation() {
        return -1;
    }
}
