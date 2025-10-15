package calculator.service;

public class CalculatorService {

    public String splitExpression(String expression) {
        if (expression.isEmpty()) {
            return "0";
        }
        return expression;
    }
}