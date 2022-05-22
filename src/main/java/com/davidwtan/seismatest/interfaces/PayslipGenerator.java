package com.davidwtan.seismatest.interfaces;

import com.davidwtan.seismatest.models.Employee;
import com.davidwtan.seismatest.models.Payslip;

import java.util.List;

public interface PayslipGenerator {
    List<Payslip> generatePayslip(List<Employee> employees);
}
