package calculator.model;

import java.util.Arrays;

public class Calculator {

    public long add(String[] numbers) {
        return Arrays.stream(numbers)
                .map(this::parseIntWithCheckEmpty)
                .peek(this::validateNegative)
                .reduce(Long::sum)
                .orElseThrow(() -> new IllegalArgumentException("올바른 값이 아닙니다."));
    }

    private Long parseIntWithCheckEmpty(String target) {
        if (target.isEmpty()) {
            return 0L;
        }

        return parseInt(target);
    }

    private Long parseInt(String target) {
        try {
            return Long.valueOf(target);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }

    private void validateNegative(Long i) {
        if (i < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        }
    }
}
