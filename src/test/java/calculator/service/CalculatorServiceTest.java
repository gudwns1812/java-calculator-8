package calculator.service;

import static org.assertj.core.api.Assertions.assertThat;

import calculator.model.Calculator;
import calculator.model.DelimiterManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorServiceTest {

    private DelimiterManager manager;
    private CalculatorService service;

    @BeforeEach
    void setUp() {
        Calculator calculator = new Calculator();
        manager = new DelimiterManager();
        service = new CalculatorService(calculator, manager);
    }

    @Test
    @DisplayName("기본 구분자를 이용해 계산한다.")
    void basic_delimiter() {
        //given
        String input = "1,2:3";
        //when
        long result = service.addNumbers(input);
        //then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("커스텀 구분자를 이용해 숫자를 계산한다.")
    void using_custom_delimiter() {
        //given
        String input = "1;2;3";
        manager.addCustomRegex(";");
        //when
        long result = service.addNumbers(input);
        //then
        assertThat(result).isEqualTo(6);
    }


}