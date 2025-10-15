package calculator.controller;

import calculator.service.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final CalculatorService service;

    public CalculatorController(CalculatorService service) {
        this.service = service;
    }

    public void run() {
        String expression = InputView.readLine();
        int result = service.splitExpression(expression);
        OutputView.printResult(result);
    }
}
