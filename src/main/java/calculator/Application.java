package calculator;

import calculator.controller.CalculatorController;
import calculator.model.Calculator;
import calculator.model.DelimiterManager;
import calculator.service.CalculatorService;
import calculator.service.DelimiterService;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Calculator calculator = new Calculator();
        DelimiterManager manager = new DelimiterManager();
        CalculatorService calculatorService = new CalculatorService(calculator, manager);
        DelimiterService delimiterService = new DelimiterService(manager);
        CalculatorController controller = new CalculatorController(calculatorService, delimiterService);

        Runtime.getRuntime().addShutdownHook(new Thread(Console::close, "shutdownHook"));

        controller.run();
    }
}
