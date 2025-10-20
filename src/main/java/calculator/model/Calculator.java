package calculator.model;

import java.util.Arrays;

public class Calculator {

    private final NumberValidator validator;
    private final NumberParser parser;

    public Calculator(NumberValidator validator, NumberParser parser) {
        this.validator = validator;
        this.parser = parser;
    }

    public long add(String[] numbers) {
        return Arrays.stream(numbers)
                .map(parser::parseIntWithCheckEmpty)
                .peek(validator::validateNegative)
                .reduce(Long::sum)
                .orElse(0L);
    }

}
