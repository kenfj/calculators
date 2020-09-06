package com.example.calculator.service;

import com.example.calculator.service.nodes.BinNode;
import com.example.calculator.service.nodes.NumberNode;
import com.example.calculator.service.nodes.UnaryNode;

public interface IVisitor {

    Number visit(NumberNode numberNode);

    Number visit(BinNode binNode);

    Number visit(UnaryNode unaryNode);

}
