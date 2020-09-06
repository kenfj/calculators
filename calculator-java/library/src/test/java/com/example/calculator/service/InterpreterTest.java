package com.example.calculator.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.calculator.service.nodes.BinNode;
import com.example.calculator.service.nodes.NumberNode;
import com.example.calculator.service.nodes.UnaryNode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InterpreterTest {

    @Test
    public void TestEmpty() {
        var node = new NumberNode(51.2);
        var actual = new Interpreter().visit(node);
        var expected = 51.2;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void TestUnary() {
        var node = new UnaryNode(TokenType.PLUS, new NumberNode(1));
        var actual = new Interpreter().visit(node);
        var expected = 1.0;

        assertThat(actual).isEqualTo(expected);

        node = new UnaryNode(TokenType.MINUS, new NumberNode(1));
        actual = new Interpreter().visit(node);
        expected = -1.0;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void TestIndividualOperations() {
        var left = new NumberNode(27);
        var right = new NumberNode(14);

        var node = new BinNode(left, TokenType.PLUS, right);
        var actual = new Interpreter().visit(node);
        var expected = 41.0;

        assertThat(actual).isEqualTo(expected);

        node = new BinNode(left, TokenType.MINUS, right);
        actual = new Interpreter().visit(node);
        expected = 13.0;

        assertThat(actual).isEqualTo(expected);

        node = new BinNode(left, TokenType.MULTIPLY, right);
        actual = new Interpreter().visit(node);
        expected = 378.0;

        assertThat(actual).isEqualTo(expected);

        node = new BinNode(left, TokenType.DIVIDE, right);
        actual = new Interpreter().visit(node);
        expected = 1.9285714285714286;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void TestFullExpression() {
        var node = new BinNode( //
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

        var actual = new Interpreter().visit(node);
        var expected = -2360.0833333333335;

        assertThat(actual).isEqualTo(expected);
    }

}
