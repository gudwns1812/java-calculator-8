package calculator;

import calculator.controller.CalculatorController;
import calculator.service.CalculatorService;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        CalculatorService service = new CalculatorService();
        CalculatorController controller = new CalculatorController(service);
        controller.run();
    }
}
