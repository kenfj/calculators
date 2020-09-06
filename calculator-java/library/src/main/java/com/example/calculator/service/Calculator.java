package com.example.calculator.service;

public class Calculator {

    public static Number calc(String text) {
        var lexer = new Lexer(text);
        var tokens = lexer.generateTokens();

        var parser = new Parser(tokens);
        var tree = parser.parse();

        var interpreter = new Interpreter();

        return tree.accept(interpreter);
    }

}
