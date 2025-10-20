package calculator.model;

public class NumberValidator {

    public void validateNegative(Long i) {
        if (i < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        }
    }
}
