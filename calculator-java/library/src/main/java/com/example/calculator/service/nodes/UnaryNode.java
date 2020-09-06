package com.example.calculator.service.nodes;

import com.example.calculator.service.IVisitor;
import com.example.calculator.service.TokenType;

public class UnaryNode implements INode {

    public final TokenType operative;
    public final INode node;

    public UnaryNode(TokenType tokenType, INode node) {
        operative = tokenType;
        this.node = node;
    }

    public Number accept(IVisitor visitor) {
        return visitor.visit(this);
    }

}
