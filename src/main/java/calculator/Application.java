package calculator;

import calculator.controller.CalculatorController;
import calculator.model.Calculator;
import calculator.model.DelimiterManager;
import calculator.service.CalculatorService;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Calculator calculator = new Calculator();
        DelimiterManager manager = new DelimiterManager();
        CalculatorService service = new CalculatorService(calculator, manager);
        CalculatorController controller = new CalculatorController(service);

        Runtime.getRuntime().addShutdownHook(new Thread(Console::close, "shutdownHook"));

        controller.run();
    }
}
