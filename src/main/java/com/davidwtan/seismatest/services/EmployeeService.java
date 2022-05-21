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
        return null;
    }

    // Don't forget to round
    private int calcPayPeriod() {
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
