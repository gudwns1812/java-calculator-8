package calculator.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorServiceTest {

    @Test
    @DisplayName("빈 문자열을 입력하면 0이 나와야한다.")
    public void 빈_문자열() {
        //given
        CalculatorService service = new CalculatorService();
        String testInput = "";
        //when
        String result = service.splitExpression(testInput);
        //then
        assertThat(result).isEqualTo("0");
    }

}