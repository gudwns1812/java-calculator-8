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
        registerCustomDelimiter(parsed);
        String regex = delimiterManager.getRegex();
        String[] numbers = getNumbers(parsed.numberString, regex);
        return calculator.add(numbers);
    }

    private void registerCustomDelimiter(ParsedExpression parsedExpression) {
        if (parsedExpression.isEmptyCustomDelimiter()) {
            return;
        }
        delimiterManager.addCustomRegex(parsedExpression.getCustomDelimiter());
    }

    private static String[] getNumbers(String numberExpression, String regex) {
        return numberExpression.split(regex);
    }

    private static class ParsedExpression {
        private final String customDelimiterString;
        private final String numberString;

        private ParsedExpression(String customDelimiterString, String numberString) {
            this.customDelimiterString = customDelimiterString;
            this.numberString = numberString;
        }

        public boolean isEmptyCustomDelimiter() {
            return customDelimiterString.isEmpty();
        }

        public String getCustomDelimiter() {
            return customDelimiterString;
        }

        public boolean hasCorrectCustomDelimiter() {
            return customDelimiterString.startsWith(START_CUSTOM_DELIMITER);
        }

        public static ParsedExpression fromExpression(String expression) {
            String[] split = expression.split(END_CUSTOM_DELIMITER);
            if (split.length == 1) {
                return new ParsedExpression("", split[0]);
            }

            String customDelimiter = split[0];
            if (!customDelimiter.startsWith(START_CUSTOM_DELIMITER)) {
                throw new IllegalArgumentException("올바른 커스텀 구분자가 아닙니다.");
            }

            int customDelimiterIndex = START_CUSTOM_DELIMITER.length();
            return new ParsedExpression(customDelimiter.substring(customDelimiterIndex), split[1]);
        }
    }
}