package com.davidwtan.seismatest.utilities;

import com.davidwtan.seismatest.utilities.Utilities;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UtilitiesTest {
    @Test
    void roundUpPointFive() {
        assertEquals(2, Utilities.roundDecimal(1.5));
    }

    void roundUp() {
        assertEquals(1, Utilities.roundDecimal(0.888));
    }

    @Test
    void roundDown() {
        assertEquals(1, Utilities.roundDecimal(1.1));
    }

    @Test
    void noDecimals() {
        assertEquals(1, Utilities.roundDecimal(1));
    }

    @Test
    void zero() {
        assertEquals(0, Utilities.roundDecimal(0.0));
    }

    @Test
    void negativeRoundUp() {
        assertEquals(-2, Utilities.roundDecimal(-1.5));
    }

    @Test
    void negativeRoundDown() {
        assertEquals(-1, Utilities.roundDecimal(-1.4));
    }
}
