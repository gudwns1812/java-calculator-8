package calculator.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator(new NumberValidator(), new NumberParser());
    }

    @Test
    @DisplayName("빈 문자열을 입력하면 0이 나와야한다.")
    void empty_expression() {
        //given
        String[] numbers = {""};
        //when
        long result = calculator.add(numbers);
        //then
        assertThat(result).isEqualTo(0L);
    }


    @Test
    @DisplayName("음수 예외 처리를 검증한다.")
    void using_custom_delimiter_negative_number() {
        //given
        String[] numbers = {"-1", "2", "3"};
        //when

        //then
        assertThatThrownBy(() -> calculator.add(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자 문자열에 문자가 들어올 경우 예외 처리를 검증한다.")
    void check_not_number_test() {
        //given
        String[] numbers = {"a", "2", "3"};
        //when

        //then
        assertThatThrownBy(() -> calculator.add(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자 사이에 빈 문자열을 0으로 처리한다.")
    void in_a_row_delimiter() {
        //given
        String[] numbers = {"1", "", "2", "3"};
        //when
        long result = calculator.add(numbers);
        //then
        assertThat(result).isEqualTo(6);
    }

}