package calculator.service;

import calculator.model.DelimiterManager;
import calculator.model.ParsedExpression;

public class DelimiterService {

    private final DelimiterManager delimiterManager;

    public DelimiterService(DelimiterManager delimiterManager) {
        this.delimiterManager = delimiterManager;
    }

    public void registerCustomDelimiter(ParsedExpression parsedExpression) {
        if (parsedExpression.isEmptyCustomDelimiter()) {
            return;
        }
        delimiterManager.addCustomRegex(parsedExpression.getCustomDelimiter());
    }
}
