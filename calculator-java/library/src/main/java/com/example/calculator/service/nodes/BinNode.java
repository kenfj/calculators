package com.example.calculator.service.nodes;

import com.example.calculator.service.IVisitor;
import com.example.calculator.service.TokenType;

public class BinNode implements INode {

    public final INode nodeA;
    public final TokenType operative;
    public final INode nodeB;

    public BinNode(INode nodeA, TokenType operative, INode nodeB) {
        this.nodeA = nodeA;
        this.operative = operative;
        this.nodeB = nodeB;
    }

    public Number accept(IVisitor visitor) {
        return visitor.visit(this);
    }

}
