package calculator.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private CalculatorService service;

    @BeforeEach
    void setUp() {
        Calculator calculator = new Calculator();
        DelimiterManager manager = new DelimiterManager();
        service = new CalculatorService(calculator, manager);
    }

    @Test
    @DisplayName("빈 문자열을 입력하면 0이 나와야한다.")
    void empty_expression() {
        //given
        String testInput = "";
        //when
        long result = service.addNumbers(testInput);
        //then
        assertThat(result).isEqualTo(0L);
    }

    @Test
    @DisplayName("커스텀 구분자를 이용해 음수 예외 처리를 검증한다.")
    void using_custom_delimiter_negative_number() {
        //given
        String input = "//;\\n-1;2;3";
        //when

        //then
        assertThatThrownBy(() -> service.addNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("커스텀 구분자를 이용해 문자 예외 처리를 검증한다.")
    void check_not_number_test() {
        //given
        String input = "//;\\na;b;c";
        //when

        //then
        assertThatThrownBy(() -> service.addNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구분자가 연속 2번 나올경우 0으로 처리한다.")
    void in_a_row_delimiter() {
        //given
        String input = "1,,2:3";
        //when
        long result = service.addNumbers(input);
        //then
        assertThat(result).isEqualTo(6);
    }

}