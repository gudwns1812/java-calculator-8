package calculator.model;

public class ParsedExpression {

    private static final String START_CUSTOM_DELIMITER = "//";
    private static final String END_CUSTOM_DELIMITER = "\\\\n";

    private final String customDelimiterString;
    private final String numberString;

    private ParsedExpression(String customDelimiterString, String numberString) {
        this.customDelimiterString = customDelimiterString;
        this.numberString = numberString;
    }

    public boolean isEmptyCustomDelimiter() {
        return customDelimiterString.isEmpty();
    }

    public String getCustomDelimiter() {
        return customDelimiterString;
    }

    public String getNumberString() {
        return numberString;
    }

    public static ParsedExpression fromExpression(String expression) {
        String[] split = expression.split(END_CUSTOM_DELIMITER);
        if (split.length == 1) {
            return new ParsedExpression("", split[0]);
        }

        String customDelimiter = split[0];
        if (!customDelimiter.startsWith(START_CUSTOM_DELIMITER)) {
            throw new IllegalArgumentException("올바른 커스텀 구분자가 아닙니다.");
        }

        int customDelimiterIndex = START_CUSTOM_DELIMITER.length();
        return new ParsedExpression(customDelimiter.substring(customDelimiterIndex), split[1]);
    }
}
