package calculator.service;

import calculator.model.Calculator;
import calculator.model.DelimiterManager;

public class CalculatorService {

    private static final String START_CUSTOM_DELIMITER = "//";
    private static final String END_CUSTOM_DELIMITER = "\\\\n";

    private final Calculator calculator;
    private final DelimiterManager delimiterManager;

    public CalculatorService(Calculator calculator, DelimiterManager delimiterManager) {
        this.calculator = calculator;
        this.delimiterManager = delimiterManager;
    }

    public long addNumbers(String expression) {
        String numberExpression = divideDelimiterAndNumber(expression);
        String regex = delimiterManager.getRegex();
        String[] numbers = numberExpression.split(regex);
        return calculator.add(numbers);
    }

    private String divideDelimiterAndNumber(String expression) {
        String[] split = expression.split(END_CUSTOM_DELIMITER);
        if (split.length == 1) {
            return split[0];
        }
        String customDelimiter = split[0];
        String numberString = split[1];

        if (!customDelimiter.startsWith(START_CUSTOM_DELIMITER)) {
            throw new IllegalArgumentException("올바른 식이 아닙니다.");
        }
        delimiterManager.addCustomRegex(customDelimiter.substring(2));

        return numberString;
    }
}