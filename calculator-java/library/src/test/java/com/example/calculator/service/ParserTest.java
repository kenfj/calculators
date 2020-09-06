package com.example.calculator.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.example.calculator.service.nodes.BinNode;
import com.example.calculator.service.nodes.NumberNode;
import com.example.calculator.service.nodes.UnaryNode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ParserTest {

        @Test
        public void TestEmpty() {
                var tokens = new ArrayList<Token>();

                var actual = new Parser(tokens).parse();

                assertThat(actual).isEqualTo(null);
        }

        @Test
        public void TestNumber() {
                var tokens = List.of(new Token(TokenType.NUMBER, 51.2));

                var actual = new Parser(tokens).parse();

                var expected = new NumberNode(51.2);

                // https://stackoverflow.com/a/27605802
                // If you're using AssertJ then use the built-in functionality
                assertThat(actual).isEqualToComparingFieldByField(expected);
        }

        @Test
        public void TestPlus() {
                var tokens = List.of( //
                                new Token(TokenType.NUMBER, 1), //
                                new Token(TokenType.PLUS), //
                                new Token(TokenType.NUMBER, 2) //
                );

                var actual = new Parser(tokens).parse();

                var expected = new BinNode( //
                                new NumberNode(1), //
                                TokenType.PLUS, //
                                new NumberNode(2) //
                );

                assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }

        @Test
        public void TestUnary() {
                var tokens = List.of( //
                                new Token(TokenType.PLUS), //
                                new Token(TokenType.NUMBER, 1) //
                );

                var actual = new Parser(tokens).parse();

                var expected = new UnaryNode( //
                                TokenType.PLUS, //
                                new NumberNode(1) //
                );

                assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

                tokens = List.of( //
                                new Token(TokenType.MINUS), //
                                new Token(TokenType.NUMBER, 1) //
                );

                actual = new Parser(tokens).parse();

                expected = new UnaryNode( //
                                TokenType.MINUS, //
                                new NumberNode(1) //
                );

                assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }

        @Test
        public void TestIndividualOperations() {
                var tokens = List.of( //
                                new Token(TokenType.NUMBER, 27), //
                                new Token(TokenType.PLUS), //
                                new Token(TokenType.NUMBER, 14) //
                );
                var actual = new Parser(tokens).parse();
                var expected = new BinNode( //
                                new NumberNode(27), //
                                TokenType.PLUS, //
                                new NumberNode(14) //
                );

                assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

                tokens = List.of( //
                                new Token(TokenType.NUMBER, 27), //
                                new Token(TokenType.MINUS), //
                                new Token(TokenType.NUMBER, 14) //
                );
                actual = new Parser(tokens).parse();
                expected = new BinNode( //
                                new NumberNode(27), //
                                TokenType.MINUS, //
                                new NumberNode(14) //
                );

                assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

                tokens = List.of( //
                                new Token(TokenType.NUMBER, 27), //
                                new Token(TokenType.MULTIPLY), //
                                new Token(TokenType.NUMBER, 14) //
                );
                actual = new Parser(tokens).parse();
                expected = new BinNode( //
                                new NumberNode(27), //
                                TokenType.MULTIPLY, //
                                new NumberNode(14) //
                );

                assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

                tokens = List.of( //
                                new Token(TokenType.NUMBER, 27), //
                                new Token(TokenType.DIVIDE), //
                                new Token(TokenType.NUMBER, 14) //
                );
                actual = new Parser(tokens).parse();
                expected = new BinNode( //
                                new NumberNode(27), //
                                TokenType.DIVIDE, //
                                new NumberNode(14) //
                );

                assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }

        @Test
        public void TestFullExpression() {
                var tokens = List.of( //
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
                                new Token(TokenType.NUMBER, 51) //
                );

                var actual = new Parser(tokens).parse();

                var expected = new BinNode( //
                                new NumberNode(27), //
                                TokenType.PLUS, //
                                new BinNode( //
                                                new BinNode( //
                                                                new BinNode( //
                                                                                new NumberNode(43), //
                                                                                TokenType.DIVIDE, //
                                                                                new NumberNode(36) //
                                                                ), //
                                                                TokenType.MINUS, //
                                                                new NumberNode(48) //
                                                ), //
                                                TokenType.MULTIPLY, //
                                                new NumberNode(51) //
                                ) //
                );

                assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }

}
