package com.example.calculator.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class Lexer {

    private static String WHITESPACE = " \n\t";
    private static String NUMBER = TokenType.NUMBER.getValue();
    private static String SYMBOLS = TokenType.getSymbols();
    private Iterator<Character> chars;
    private Character currentChar;

    public Lexer(String text) {
        chars = getIterator(text);
        advance();
    }

    private void advance() {
        if (chars.hasNext())
            currentChar = chars.next();
        else
            currentChar = null;
    }

    public List<Token> generateTokens() {
        List<Token> tokens = new ArrayList<>();

        while (currentChar != null) {
            if (WHITESPACE.contains(currentChar.toString()))
                advance();
            else if (NUMBER.contains(currentChar.toString()))
                tokens.add(generateNumber());
            else if (SYMBOLS.contains(currentChar.toString()))
                tokens.add(generateSymbol());
            else
                throw new RuntimeException("Illegal Char " + currentChar);
        }

        return tokens;
    }

    private Token generateNumber() {
        String numberStr = "";

        while (currentChar != null && NUMBER.contains(currentChar.toString())) {
            numberStr += currentChar;
            advance();
        }

        if (numberStr.chars().filter(x -> x == '.').count() > 1)
            throw new RuntimeException("too many dot in " + numberStr);
        if (numberStr.endsWith("."))
            numberStr += "0";

        double value = Double.parseDouble(numberStr);
        return new Token(TokenType.NUMBER, value);
    }

    private Token generateSymbol() {
        var tokenType = TokenType.fromValue(currentChar.toString());
        advance();
        return new Token(tokenType);
    }

    private Iterator<Character> getIterator(String text) {
        var arr = text.toCharArray();
        var cStream = IntStream.range(0, arr.length).mapToObj(i -> arr[i]);
        return cStream.iterator();
    }
}
