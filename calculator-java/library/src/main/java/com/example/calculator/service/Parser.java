package com.example.calculator.service;

import java.util.Iterator;
import java.util.List;

import com.example.calculator.service.nodes.BinNode;
import com.example.calculator.service.nodes.INode;
import com.example.calculator.service.nodes.NumberNode;
import com.example.calculator.service.nodes.UnaryNode;

public class Parser {

    private Iterator<Token> tokens;
    private Token currentToken;
    private List<TokenType> PlusMinus = List.of(TokenType.PLUS, TokenType.MINUS);
    private List<TokenType> MultiDiv = List.of(TokenType.MULTIPLY, TokenType.DIVIDE);

    public Parser(List<Token> tokenList) {
        tokens = tokenList.iterator();
        advance();
    }

    private void advance() {
        if (tokens.hasNext())
            currentToken = tokens.next();
        else
            currentToken = null;
    }

    public INode parse() {
        if (currentToken != null)
            return expr();
        else
            return null;
    }

    private INode expr() {
        var result = term();

        while (currentToken != null) {
            if (PlusMinus.contains(currentToken.tokenType)) {
                var operator_ = currentToken.tokenType;
                advance();
                result = new BinNode(result, operator_, term());
            } else {
                break;
            }
        }

        return result;
    }

    private INode term() {
        var result = factor();

        while (currentToken != null) {
            if (MultiDiv.contains(currentToken.tokenType)) {
                var operator_ = currentToken.tokenType;
                advance();
                result = new BinNode(result, operator_, factor());
            } else {
                break;
            }
        }

        return result;
    }

    private INode factor() {
        var token = currentToken;

        if (token.tokenType == TokenType.LPAREN) {
            advance();

            var result = expr();

            if (currentToken.tokenType != TokenType.RPAREN)
                throw new RuntimeException("Invalid syntax");

            advance();
            return result;
        }

        if (token.tokenType == TokenType.NUMBER) {
            advance();
            return new NumberNode(token.value);
        } else if (PlusMinus.contains(token.tokenType)) {
            advance();
            return new UnaryNode(token.tokenType, factor());
        }

        throw new RuntimeException("Invalid syntax");
    }

}
