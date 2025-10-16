package calculator.model;

import java.util.Arrays;

public class Calculator {

    public int add(String[] numbers) {
        return Arrays.stream(numbers)
                .map(Integer::parseInt)
                .peek(Calculator::validateNegative)
                .reduce(Integer::sum)
                .orElseThrow(() -> new IllegalArgumentException("올바른 값이 아닙니다."));
    }

    private static void validateNegative(Integer i) {
        if (i < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        }
    }
}
