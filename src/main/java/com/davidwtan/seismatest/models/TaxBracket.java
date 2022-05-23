package com.davidwtan.seismatest.models;

// Tax brackets [min, max] (inclusive)
// E.g. $0 - $18,200 ; $18,201 - $37,000 ; ...
public class TaxBracket {
    private int min;
    private int max;

    public TaxBracket(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "$" + min + " - $" + max;
    }
}
