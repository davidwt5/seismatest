package com.davidwtan.seismatest.utilities;

import static java.lang.Math.abs;

public class Utilities {
    public static int roundDecimal(double decimal) {
        int sign = decimal < 0 ? -1 : 1; // negative or positive sign
        decimal = abs(decimal);
        int roundedDown = (int) decimal;
        return sign * (decimal - roundedDown >= 0.5 ? roundedDown+1 : roundedDown);
    }
}
