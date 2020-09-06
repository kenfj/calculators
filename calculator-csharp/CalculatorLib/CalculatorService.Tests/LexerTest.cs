using System;
using System.Linq;
using System.Collections.Generic;

using Xunit;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using DeepEqual.Syntax;


namespace CalculatorService.Tests
{
    public class LexerTest
    {
        [Fact]
        public void TestEmpty()
        {
            var lexer = new Lexer("");
            var actual = lexer.GenerateTokens().ToList();

            var expected = new List<string>();
            CollectionAssert.AreEqual(expected, actual);
        }

        [Fact]
        public void TestEmpty2()
        {
            var lexer = new Lexer(" \t\n \t\t\n");
            var actual = lexer.GenerateTokens().ToList();

            var expected = new List<string>();
            CollectionAssert.AreEqual(expected, actual);
        }

        [Fact]
        public void TestNumbers()
        {
            var text = "123 123.456 123. .456 .";
            var actual = new Lexer(text).GenerateTokens().ToList();

            var expected = new List<Token>()
            {
                new Token(TokenType.NUMBER, 123.000),
                new Token(TokenType.NUMBER, 123.456),
                new Token(TokenType.NUMBER, 123.000),
                new Token(TokenType.NUMBER, 000.456),
                new Token(TokenType.NUMBER, 000.000),
            };

            expected.ShouldDeepEqual(actual);
        }

        [Fact]
        public void TestOperators()
        {
            var actual = new Lexer("+-*/").GenerateTokens().ToList();

            var expected = new List<Token>() {
                new Token(TokenType.PLUS),
                new Token(TokenType.MINUS),
                new Token(TokenType.MULTIPLY),
                new Token(TokenType.DIVIDE),
            };

            expected.ShouldDeepEqual(actual);
        }

        [Fact]
        public void TestParens()
        {
            var actual = new Lexer("()").GenerateTokens().ToList();

            var expected = new List<Token>() {
                new Token(TokenType.LPAREN),
                new Token(TokenType.RPAREN),
            };

            expected.ShouldDeepEqual(actual);
        }

        [Fact]
        public void TestAll()
        {
            var actual = new Lexer("27 + (43 / 36 - 48) * 51").GenerateTokens().ToList();

            var expected = new List<Token>() {
                new Token(TokenType.NUMBER, 27),
                new Token(TokenType.PLUS),
                new Token(TokenType.LPAREN),
                new Token(TokenType.NUMBER, 43),
                new Token(TokenType.DIVIDE),
                new Token(TokenType.NUMBER, 36),
                new Token(TokenType.MINUS),
                new Token(TokenType.NUMBER, 48),
                new Token(TokenType.RPAREN),
                new Token(TokenType.MULTIPLY),
                new Token(TokenType.NUMBER, 51),
            };

            actual.ShouldDeepEqual(expected);
        }
    }
}
