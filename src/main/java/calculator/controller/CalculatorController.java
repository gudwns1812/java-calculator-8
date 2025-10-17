package calculator.controller;

import calculator.model.ParsedExpression;
import calculator.service.CalculatorService;
import calculator.service.DelimiterService;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final CalculatorService calculatorService;
    private final DelimiterService delimiterService;

    public CalculatorController(CalculatorService calculatorService, DelimiterService delimiterService) {
        this.calculatorService = calculatorService;
        this.delimiterService = delimiterService;
    }

    public void run() {
        String expression = InputView.readLine();
        ParsedExpression parsed = ParsedExpression.fromExpression(expression);
        delimiterService.registerCustomDelimiter(parsed);
        long result = calculatorService.addNumbers(parsed.getNumberString());
        OutputView.printResult(result);
    }
}
