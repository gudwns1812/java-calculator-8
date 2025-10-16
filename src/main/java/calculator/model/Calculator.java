package calculator.model;

import java.util.Arrays;

public class Calculator {

    public int add(String[] numbers) {
        return Arrays.stream(numbers)
                .map(this::parseIntWithCheckEmpty)
                .peek(this::validateNegative)
                .reduce(Integer::sum)
                .orElseThrow(() -> new IllegalArgumentException("올바른 값이 아닙니다."));
    }

    private int parseIntWithCheckEmpty(String target) {
        if (target.isEmpty()) {
            return 0;
        }

        return parseInt(target);
    }

    private int parseInt(String target) {
        try {
            return Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }

    private void validateNegative(Integer i) {
        if (i < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        }
    }
}
