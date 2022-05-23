package com.davidwtan.seismatest.models;

import com.davidwtan.seismatest.utilities.Utilities;

// Example: Nil ; 19c for each $1 over $18,200 ; $3,572 plus 32.5c for each $1 over $37,000
public class TaxFormula {
    private int startingValue;
    private double extraRate;
    private int bracketMinimum;

    public TaxFormula(int startingValue, double extraRate, int bracketMinimum) {
        this.startingValue = startingValue;
        this.extraRate = extraRate;
        this.bracketMinimum = bracketMinimum;
    }

    /* From looking at the income tax table, it seems like the formula for monthly income tax is as follows:
     *  (startingValue + (annualSalary - bracketMinimum) * extraRate) / 12
     *  For example, someone with an annual income of $60,050 will be taxed as follows:
     *  (3,572 + (60,050 - 37,000) * 0.325) / 12 = 922 (rounded up)
     */
    public int calcIncomeTax(int annualSalary) {
        return Utilities.roundDecimal(
                (startingValue + (annualSalary - bracketMinimum) * extraRate) / 12.0
        );
    }
}
