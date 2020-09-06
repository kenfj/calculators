package com.example.calculator.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {

    @Test
    public void BasicTest() {
        var actual = Calculator.calc("1");

        assertThat(actual).isEqualTo(1.0);

        actual = Calculator.calc("1 * (2 + 3) + 4 * 5");
        assertThat(actual).isEqualTo(25.0);

        actual = Calculator.calc("-1");
        assertThat(actual).isEqualTo(-1.0);

        actual = Calculator.calc("- 1 * ((2 + 3) + 4 * (5 - 6)) + 7");
        assertThat(actual).isEqualTo(6.0);

        actual = Calculator.calc("1 / 0");
        assertThat(actual).isEqualTo(Double.POSITIVE_INFINITY);
    }

}
