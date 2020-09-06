package com.example.calculator.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LexerTest {

    @Test
    public void testEmpty() {
        var lexer = new Lexer("");
        var actual = lexer.generateTokens();

        var expected = new ArrayList<Token>();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void TestEmpty2() {
        var lexer = new Lexer(" \t\n \t\t\n");
        var actual = lexer.generateTokens();

        var expected = new ArrayList<Token>();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void TestNumbers() {
        var text = "123 123.456 123. .456 .";
        var actual = new Lexer(text).generateTokens();

        var expected = List.of( //
                new Token(TokenType.NUMBER, 123.000), //
                new Token(TokenType.NUMBER, 123.456), //
                new Token(TokenType.NUMBER, 123.000), //
                new Token(TokenType.NUMBER, 000.456), //
                new Token(TokenType.NUMBER, 000.000) //
        );

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void TestOperators() {
        var actual = new Lexer("+-*/").generateTokens();

        var expected = List.of( //
                new Token(TokenType.PLUS), //
                new Token(TokenType.MINUS), //
                new Token(TokenType.MULTIPLY), //
                new Token(TokenType.DIVIDE) //
        );

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void TestParens() {
        var actual = new Lexer("()").generateTokens();

        var expected = List.of( //
                new Token(TokenType.LPAREN), //
                new Token(TokenType.RPAREN) //
        );

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void TestAll() {
        var actual = new Lexer("27 + (43 / 36 - 48) * 51").generateTokens();

        var expected = List.of( //
                new Token(TokenType.NUMBER, 27), //
                new Token(TokenType.PLUS), //
                new Token(TokenType.LPAREN), //
                new Token(TokenType.NUMBER, 43), //
                new Token(TokenType.DIVIDE), //
                new Token(TokenType.NUMBER, 36), //
                new Token(TokenType.MINUS), //
                new Token(TokenType.NUMBER, 48), //
                new Token(TokenType.RPAREN), //
                new Token(TokenType.MULTIPLY), //
                new Token(TokenType.NUMBER, 51));

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
