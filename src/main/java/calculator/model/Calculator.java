package calculator.model;

import java.util.Arrays;

public class Calculator {

    public long add(String[] numbers) {
        return Arrays.stream(numbers)
                .map(Calculator::parseIntWithCheckEmpty)
                .peek(Calculator::validateNegative)
                .reduce(Long::sum)
                .orElse(0L);
    }

    private static Long parseIntWithCheckEmpty(String target) {
        if (target.isEmpty()) {
            return 0L;
        }

        return parseInt(target);
    }

    private static Long parseInt(String target) {
        try {
            return Long.valueOf(target);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }

    private static void validateNegative(Long i) {
        if (i < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        }
    }
}
