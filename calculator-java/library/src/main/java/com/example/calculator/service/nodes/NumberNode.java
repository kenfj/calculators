package com.example.calculator.service.nodes;

import com.example.calculator.service.IVisitor;

public class NumberNode implements INode {

    public final double value;

    public NumberNode(Number value) {
        this.value = value.doubleValue();
    }

    public Number accept(IVisitor visitor) {
        return visitor.visit(this);
    }

}
