package calculator.model;

public class ParsedExpression {

    private static final String START_CUSTOM_DELIMITER = "//";
    private static final String END_CUSTOM_DELIMITER = "\\n";

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

        if (expression.indexOf(END_CUSTOM_DELIMITER) != expression.lastIndexOf(END_CUSTOM_DELIMITER)) {
            throw new IllegalArgumentException("구분자가 여러개 입니다.");
        }

        if (!expression.startsWith(START_CUSTOM_DELIMITER)) {
            return new ParsedExpression("", expression);
        }

        int endCustomDelimiter = expression.indexOf(END_CUSTOM_DELIMITER);
        int startCustomDelimiter = START_CUSTOM_DELIMITER.length();
        String customDelimiter = expression.substring(startCustomDelimiter, endCustomDelimiter);
        String numberStr = expression.substring(endCustomDelimiter + END_CUSTOM_DELIMITER.length());

        return new ParsedExpression(customDelimiter, numberStr);
    }

}
