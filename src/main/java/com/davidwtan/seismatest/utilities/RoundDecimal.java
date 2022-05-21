package com.davidwtan.seismatest.utilities;

public class RoundDecimal {
    public int roundDecimal(double decimal) {
        int roundedDown = (int) decimal;
        return decimal - roundedDown >= 0.5 ? roundedDown+1 : roundedDown;
    }
}
