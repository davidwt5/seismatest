package com.davidwtan.seismatest.models;

public class Payslip {
    private Employee employee;
    private String fromDate;
    private String toDate;
    private int grossIncome;
    private int incomeTax;
    private int superannuation;
    private int netIncome;

    public Payslip(Employee employee, String fromDate, String toDate, int grossIncome, int incomeTax, int superannuation, int netIncome) {
        this.employee = employee;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.grossIncome = grossIncome;
        this.incomeTax = incomeTax;
        this.superannuation = superannuation;
        this.netIncome = netIncome;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(int grossIncome) {
        this.grossIncome = grossIncome;
    }

    public int getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(int incomeTax) {
        this.incomeTax = incomeTax;
    }

    public int getSuperannuation() {
        return superannuation;
    }

    public void setSuperannuation(int superannuation) {
        this.superannuation = superannuation;
    }

    public int getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(int netIncome) {
        this.netIncome = netIncome;
    }

    @Override
    public String toString() {
        return "Payslip{" +
                "employee=" + employee +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", grossIncome=" + grossIncome +
                ", incomeTax=" + incomeTax +
                ", superannuation=" + superannuation +
                ", netIncome=" + netIncome +
                '}';
    }
}
