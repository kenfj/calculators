package com.example.calculator.service;

public class Token {

    public final TokenType tokenType;
    public final Double value;

    public Token(TokenType tokenType) {
        this.tokenType = tokenType;
        this.value = null;
    }

    public Token(TokenType tokenType, double value) {
        this.tokenType = tokenType;
        this.value = value;
    }

}
