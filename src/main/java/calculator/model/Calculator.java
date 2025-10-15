package calculator.model;

import java.util.Arrays;

public class Calculator {

    public int add(String[] numbers) {
        return Arrays.stream(numbers)
                .map(Integer::parseInt)
                .reduce(Integer::sum)
                .orElseThrow(() -> new IllegalArgumentException("올바른 값이 아닙니다."));
    }
}
