package com.davidwtan.seismatest.services;

import com.davidwtan.seismatest.models.TaxBracket;
import com.davidwtan.seismatest.models.TaxFormula;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaxService {
    // Calculates the IncomeTax at a specific annual salary point
    public int calcIncomeTax(int annualSalary) {
        Map<TaxBracket, TaxFormula> taxBracketToTaxFormulaMap = getTaxBracketMapping();

        TaxBracket taxBracket = getTaxBracket(taxBracketToTaxFormulaMap, annualSalary);
        TaxFormula taxFormula = taxBracketToTaxFormulaMap.get(taxBracket);
        return taxFormula.calcIncomeTax(annualSalary);
    }

    // Assumption #1: Associated data is ordered at the same index
    // E.g.: Lowest Tax bracket will be at index 0 of the TaxBracket list and formula for that will also be at index 0
    // Assumption #2: The number of brackets are relatively small, therefore O(n) time and O(n) space is not an issue.
    // Of the TaxFormula list
    public Map<TaxBracket,TaxFormula> getTaxBracketMapping() {
        List<TaxBracket> taxBrackets = getTaxBrackets();
        List<TaxFormula> taxFormulas = getTaxFormulas();
        Map<TaxBracket,TaxFormula> map = new HashMap<>();
        for(int i=0; i<taxBrackets.size(); i++) {
            map.put(taxBrackets.get(i), taxFormulas.get(i));
        }

        return map;
    }

    // Gets the tax bracket based on annualSalary
    private TaxBracket getTaxBracket(Map<TaxBracket, TaxFormula> map, int annualSalary) {
        return map.keySet().stream()
                .filter(key -> annualSalary >= key.getMin() && annualSalary <= key.getMax())
                .toList()
                .get(0);
    }

    // Assumption #1: Tax formulas stay the same, but the specific values may change
    // Assumption #2: Data is ordered in the same way as its associated TaxFormulas
    // Can get data from read a variety of sources (JSON file, CSV file, Over the network, DB, etc.)
    // For now, it's hardcoded
    private List<TaxBracket> getTaxBrackets() {
        return Arrays.asList(
                new TaxBracket(0, 18200),
                new TaxBracket(18201, 37000),
                new TaxBracket(37001, 87000),
                new TaxBracket(87001, 180000),
                new TaxBracket(180001, Integer.MAX_VALUE)
        );
    }

    // Assumption #1: Tax formulas stay the same, but the specific values may change
    // Can get data from read a variety of sources (JSON file, CSV file, Over the network, DB, etc.)
    // For now, it's hardcoded
    private List<TaxFormula> getTaxFormulas() {
        return Arrays.asList(
                new TaxFormula(0, 0, 0),
                new TaxFormula(0, 0.19, 18200),
                new TaxFormula(3572, 0.325, 37000),
                new TaxFormula(19822, 0.37, 87000),
                new TaxFormula(54232, 0.45, 180000)
        );
    }
}
