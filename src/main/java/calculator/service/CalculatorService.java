package calculator.service;

import calculator.model.Calculator;
import calculator.model.DelimiterManager;
import calculator.model.ParsedExpression;

public class CalculatorService {

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
        String[] numbers = getNumbers(parsed.getNumberString(), regex);
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

}