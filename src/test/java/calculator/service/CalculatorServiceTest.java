package calculator.service;

import static org.assertj.core.api.Assertions.assertThat;

import calculator.model.Calculator;
import calculator.model.DelimiterManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorServiceTest {

    private Calculator calculator;
    private DelimiterManager manager;
    private CalculatorService service;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        manager = new DelimiterManager();
        service = new CalculatorService(calculator, manager);
    }

    @Test
    @DisplayName("빈 문자열을 입력하면 0이 나와야한다.")
    public void empty_expression() {
        //given
        String testInput = "";
        //when
        int result = service.splitExpression(testInput);
        //then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("기본 구분자를 이용해 계산한다.")
    public void basic_delimiter() {
        //given
        String input = "1,2:3";
        //when
        int result = service.splitExpression(input);
        //then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자를 이용해 숫자를 계산한다.")
    public void using_custom_delimiter() {
        //given
        String input = "//;\\n1;2;3";
        //when
        int result = service.splitExpression(input);
        //then
        assertThat(result).isEqualTo(6);
    }
}