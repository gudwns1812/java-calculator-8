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
        ParsedExpression parsed = ParsedExpression.fromExpression(expression);
        registerDelimiter(parsed);
        String regex = delimiterManager.getRegex();
        String[] numbers = parsed.numberString().split(regex);
        return calculator.add(numbers);
    }


    private void registerDelimiter(ParsedExpression parsedExpression) {
        if (parsedExpression.isEmptyCustomDelimiter()) {
            return;
        }
        if (!parsedExpression.hasCorrectCustomDelimiter()) {
            throw new IllegalArgumentException("올바른 식이 아닙니다.");
        }
        delimiterManager.addCustomRegex(parsedExpression.getCustomDelimiter());
    }

    private record ParsedExpression(String customDelimiterString, String numberString) {

        public boolean isEmptyCustomDelimiter() {
            return customDelimiterString.isEmpty();
        }

        public String getCustomDelimiter() {
            return customDelimiterString.substring(2);
        }

        public boolean hasCorrectCustomDelimiter() {
            return customDelimiterString.startsWith(START_CUSTOM_DELIMITER);
        }

        public static ParsedExpression fromExpression(String expression) {
            String[] split = expression.split(END_CUSTOM_DELIMITER);
            if (split.length == 1) {
                return new ParsedExpression("", split[0]);
            }
            return new ParsedExpression(split[0], split[1]);
        }
    }
}