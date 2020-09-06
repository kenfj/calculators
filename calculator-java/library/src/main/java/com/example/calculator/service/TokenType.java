package com.example.calculator.service;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * NUMBER, PLUS, MINUS, ... etc.
 */
public enum TokenType {

    NUMBER("0123456789."), //
    PLUS("+"), //
    MINUS("-"), //
    MULTIPLY("*"), //
    DIVIDE("/"), //
    LPAREN("("), //
    RPAREN(")");

    private String value;

    private TokenType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getSymbols() {
        return "+-*/()";
    }

    // Convert value to matching Java Enum
    // https://stackoverflow.com/a/37264974
    private static final Map<String, TokenType> map;

    static {
        map = Arrays.stream(values()).collect(Collectors.toMap(e -> e.value, e -> e));
    }

    public static TokenType fromValue(String value) {
        return Optional.ofNullable(map.get(value)).orElseThrow();
    }
}
