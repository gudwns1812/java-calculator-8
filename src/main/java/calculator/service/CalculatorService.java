package calculator.service;

import calculator.model.Calculator;
import calculator.model.DelimiterManager;

public class CalculatorService {

    private final Calculator calculator;
    private final DelimiterManager delimiterManager;

    public CalculatorService(Calculator calculator, DelimiterManager delimiterManager) {
        this.calculator = calculator;
        this.delimiterManager = delimiterManager;
    }

    public long addNumbers(String numberString) {
        String regex = delimiterManager.getRegex();
        String[] numbers = numberString.split(regex);
        return calculator.add(numbers);
    }
}