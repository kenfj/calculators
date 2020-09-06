package com.example.calculator.service.nodes;

import com.example.calculator.service.IVisitor;

public interface INode {
    Number accept(IVisitor visitor);
}
