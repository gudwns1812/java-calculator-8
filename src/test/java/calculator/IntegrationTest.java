package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.model.Calculator;
import calculator.model.DelimiterManager;
import calculator.model.NumberParser;
import calculator.model.NumberValidator;
import calculator.model.ParsedExpression;
import calculator.service.CalculatorService;
import calculator.service.DelimiterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntegrationTest {
    private DelimiterManager manager;
    private CalculatorService calculatorService;
    private DelimiterService delimiterService;

    @BeforeEach
    void setUp() {
        Calculator calculator = new Calculator(new NumberValidator(), new NumberParser());
        manager = new DelimiterManager();
        calculatorService = new CalculatorService(calculator, manager);
        delimiterService = new DelimiterService(manager);
    }


    @Test
    @DisplayName("커스텀 구분자와 기본 구분자를 혼합해 사용한다.")
    void custom_and_basic_delimiter() {
        //given
        String input = "//;\\n1;2,3:4";
        //when
        ParsedExpression parsed = ParsedExpression.fromExpression(input);
        delimiterService.registerCustomDelimiter(parsed);
        long result = calculatorService.addNumbers(parsed.getNumberString());
        //then
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("커스텀 구분자가 한자리가 아닌 경우 테스트")
    void multi_digits_delimiter() {
        //given
        String input = "//!!!;;\\n1!!!;;2!!!;;3";
        //when
        ParsedExpression parsed = ParsedExpression.fromExpression(input);
        delimiterService.registerCustomDelimiter(parsed);
        long result = calculatorService.addNumbers(parsed.getNumberString());
        //then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 문자열만 입력하면 0이 나와야한다.")
    void empty_expression_with_custom_delimiter() {
        //given
        String input = "//;\\n";
        //when
        ParsedExpression parsed = ParsedExpression.fromExpression(input);
        delimiterService.registerCustomDelimiter(parsed);
        long result = calculatorService.addNumbers(parsed.getNumberString());
        //then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("\n 문자열이 여러번 등장 하는 경우")
    void multiple_END_CUSTOM_DELIMITER() {
        //given
        String input = "//;\\n1;\\n2";
        //when
        //then
        assertThatThrownBy(() -> ParsedExpression.fromExpression(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
