package com.davidwtan.seismatest.services;

import com.davidwtan.seismatest.interfaces.PayslipGenerator;
import com.davidwtan.seismatest.models.Employee;
import com.davidwtan.seismatest.models.PayPeriod;
import com.davidwtan.seismatest.models.Payslip;
import com.davidwtan.seismatest.utilities.Utilities;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements PayslipGenerator {
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
         if(annualSalary > 18200 && annualSalary <=37000)
             return calcIncomeTax(annualSalary, 0, 0.19, 18200);
         else if(annualSalary > 37000 && annualSalary <= 87000)
             return calcIncomeTax(annualSalary, 3572, 0.325, 37000);
         else if(annualSalary > 87000 && annualSalary <= 180000)
             return calcIncomeTax(annualSalary, 19822, 0.37, 87000);
         else if(annualSalary > 180000)
             return calcIncomeTax(annualSalary, 54232, 0.45, 180000);
         else
             return 0;
    }

    /* From looking at the income tax table, it seems like the formula for monthly income tax is as follows:
     *  (startingValue + (annualSalary - bracketMinimum) * extraRate) / 12
     *  For example, someone with an annual income of $60,050 will be taxed as follows:
     *  (3,572 + (60,050 - 37,000) * 0.325) / 12 = 922 (rounded up)
     */
    private int calcIncomeTax(int annualSalary, int startingValue, double extraRate, int bracketMinimum) {
        return Utilities.roundDecimal(
                (startingValue + (annualSalary - bracketMinimum) * extraRate) / 12.0
        );
    }

    private int calcNetIncome(int grossIncome, int incomeTax) {
        return grossIncome - incomeTax;
    }

    private int calcSuperannuation(int grossIncome, double superRate) {
        return Utilities.roundDecimal(grossIncome * superRate);
    }
}
