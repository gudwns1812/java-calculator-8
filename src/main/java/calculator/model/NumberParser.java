package calculator.model;

public class NumberParser {

    public Long parseIntWithCheckEmpty(String target) {
        if (target.isEmpty()) {
            return 0L;
        }

        return parseInt(target);
    }

    public Long parseInt(String target) {
        try {
            return Long.valueOf(target);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }
}
