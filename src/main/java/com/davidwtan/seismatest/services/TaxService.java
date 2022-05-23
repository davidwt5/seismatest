package com.davidwtan.seismatest.services;

import com.davidwtan.seismatest.datafetchers.TaxBracketFetcher;
import com.davidwtan.seismatest.models.TaxBracket;
import com.davidwtan.seismatest.models.TaxFormula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaxService {
    private final TaxBracketFetcher taxBracketFetcher;
    @Autowired
    public TaxService(TaxBracketFetcher taxBracketFetcher) {
        this.taxBracketFetcher = taxBracketFetcher;
    }

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
        List<TaxBracket> taxBrackets = taxBracketFetcher.getTaxBrackets();
        List<TaxFormula> taxFormulas = taxBracketFetcher.getTaxFormulas();
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
}
