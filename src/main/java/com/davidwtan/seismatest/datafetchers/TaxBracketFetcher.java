package com.davidwtan.seismatest.datafetchers;

import com.davidwtan.seismatest.models.TaxBracket;
import com.davidwtan.seismatest.models.TaxFormula;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

// Responsible for obtaining data regarding taxes, whether its from an API, DB lookup, reading from a file, etc.
// Everything is hardcoded for now but this is easily extendable to consume an API for example
@Component
public class TaxBracketFetcher {

    // Assumption #1: Tax formulas stay the same, but the specific values may change
    // Assumption #2: Data is ordered in the same way as its associated TaxFormula counterpart
    // Using the sample tax brackets as an example: The "$87,001 - $180,000" tax bracket is at index=3
    public List<TaxBracket> getTaxBrackets() {
        return Arrays.asList(
                new TaxBracket(0, 18200),
                new TaxBracket(18201, 37000),
                new TaxBracket(37001, 87000),
                new TaxBracket(87001, 180000),
                new TaxBracket(180001, Integer.MAX_VALUE)
        );
    }

    // Assumption #1: Tax formulas stay the same, but the specific values may change
    // Assumption #2: Data is ordered in the same way as its associated TaxBrackets counterpart
    // Using the sample tax brackets as an example: The "$19,822 plus 37c for each $1 over $87,000" formula is at index=3
    public List<TaxFormula> getTaxFormulas() {
        return Arrays.asList(
                new TaxFormula(0, 0, 0),
                new TaxFormula(0, 0.19, 18200),
                new TaxFormula(3572, 0.325, 37000),
                new TaxFormula(19822, 0.37, 87000),
                new TaxFormula(54232, 0.45, 180000)
        );
    }
}
