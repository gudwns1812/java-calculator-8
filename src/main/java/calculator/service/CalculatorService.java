package calculator.service;

import calculator.model.Calculator;

public class CalculatorService {

    private static final String BASIC_DELIMITER = "[,:]";

    private final Calculator calculator;

    public CalculatorService(Calculator calculator) {
        this.calculator = calculator;
    }

    public int splitExpression(String expression) {
        if (expression.isEmpty()) {
            return 0;
        }
        String[] numbers = expression.split(BASIC_DELIMITER);
        return calculator.add(numbers);
    }
}