package com.example.calculator.service;

import com.example.calculator.service.nodes.BinNode;
import com.example.calculator.service.nodes.NumberNode;
import com.example.calculator.service.nodes.UnaryNode;

public class Interpreter implements IVisitor {

    @Override
    public Number visit(NumberNode node) {
        return node.value;
    }

    @Override
    public Number visit(BinNode node) {
        var left = node.nodeA.accept(this);
        var right = node.nodeB.accept(this);
        double value;

        if (node.operative == TokenType.PLUS)
            value = left.doubleValue() + right.doubleValue();
        else if (node.operative == TokenType.MINUS)
            value = left.doubleValue() - right.doubleValue();
        else if (node.operative == TokenType.MULTIPLY)
            value = left.doubleValue() * right.doubleValue();
        else if (node.operative == TokenType.DIVIDE)
            value = left.doubleValue() / right.doubleValue();
        else
            throw new RuntimeException("Invalid operator " + node.operative);

        return value;
    }

    @Override
    public Number visit(UnaryNode node) {
        var value = node.node.accept(this);

        if (node.operative == TokenType.MINUS)
            value = -1 * value.doubleValue();

        return value;
    }

}
