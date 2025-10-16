package calculator.service;

import static org.assertj.core.api.Assertions.assertThat;

import calculator.model.Calculator;
import calculator.model.DelimiterManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorServiceTest {

    private CalculatorService service;

    @BeforeEach
    void setUp() {
        Calculator calculator = new Calculator();
        DelimiterManager manager = new DelimiterManager();
        service = new CalculatorService(calculator, manager);
    }

    @Test
    @DisplayName("기본 구분자를 이용해 계산한다.")
    void basic_delimiter() {
        //given
        String input = "1,2:3";
        //when
        int result = service.splitExpression(input);
        //then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자를 이용해 숫자를 계산한다.")
    void using_custom_delimiter() {
        //given
        String input = "//;\\n1;2;3";
        //when
        int result = service.splitExpression(input);
        //then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자와 기본 구분자를 혼합해 사용한다.")
    void custom_and_basic_delimiter() {
        //given
        String input = "//;\\n1;2,3:4";
        //when
        int result = service.splitExpression(input);
        //then
        assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("커스텀 구분자가 한자리가 아닌 경우 테스트")
    void multi_digits_delimiter() {
        //given
        String input = "//!!!;;\\n1!!!;;2!!!;;3";
        //when
        int result = service.splitExpression(input);
        //then
        assertThat(result).isEqualTo(6);
    }


}