package com.davidwtan.seismatest;

import com.davidwtan.seismatest.utilities.RoundDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoundDecimalTest {
	@Test
	void roundUpPointFive() {
		var roundDecimal = new RoundDecimal();
		assertEquals(2, roundDecimal.roundDecimal(1.5));
	}

	void roundUp() {
		var roundDecimal = new RoundDecimal();
		assertEquals(1, roundDecimal.roundDecimal(0.888));
	}

	@Test
	void roundDown() {
		var roundDecimal = new RoundDecimal();
		assertEquals(1, roundDecimal.roundDecimal(1.1));
	}

	@Test
	void noDecimals() {
		var roundDecimal = new RoundDecimal();
		assertEquals(1, roundDecimal.roundDecimal(1));
	}

	@Test
	void zero() {
		var roundDecimal = new RoundDecimal();
		assertEquals(0, roundDecimal.roundDecimal(0.0));
	}

	@Test
	void negativeRoundUp() {
		var roundDecimal = new RoundDecimal();
		assertEquals(-2, roundDecimal.roundDecimal(-1.5));
	}

	@Test
	void negativeRoundDown() {
		var roundDecimal = new RoundDecimal();
		assertEquals(-1, roundDecimal.roundDecimal(-1.4));
	}
}
