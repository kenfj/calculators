using System;
using System.Collections.Generic;

using Xunit;
using DeepEqual.Syntax;

using CalculatorService.Nodes;


namespace CalculatorService.Tests
{
    public class ParserTest
    {
        [Fact]
        public void TestEmpty()
        {
            var tokens = new List<Token>();

            var node = new Parser(tokens).Parse();

            Assert.Null(node);
        }

        [Fact]
        public void TestNumber()
        {
            var tokens = new List<Token>() { new Token(TokenType.NUMBER, 51.2) };

            var expected = new NumberNode(51.2);
            var actual = new Parser(tokens).Parse();

            expected.ShouldDeepEqual(actual);
        }

        [Fact]
        public void TestPlus()
        {
            var tokens = new List<Token>() {
                new Token(TokenType.NUMBER, 1),
                new Token(TokenType.PLUS),
                new Token(TokenType.NUMBER, 2),
            };

            var expected = new BinNode(
                new NumberNode(1),
                TokenType.PLUS,
                new NumberNode(2)
            );

            var actual = new Parser(tokens).Parse();

            expected.ShouldDeepEqual(actual);
        }

        [Fact]
        public void TestUnary()
        {
            var tokens = new List<Token>() {
                new Token(TokenType.PLUS),
                new Token(TokenType.NUMBER, 1),
            };
            var expected = new UnaryNode(
                TokenType.PLUS,
                new NumberNode(1)
            );

            var actual = new Parser(tokens).Parse();
            expected.ShouldDeepEqual(actual);

            tokens = new List<Token>() {
                new Token(TokenType.MINUS),
                new Token(TokenType.NUMBER, 1),
            };
            expected = new UnaryNode(
                TokenType.MINUS,
                new NumberNode(1)
            );

            actual = new Parser(tokens).Parse();
            expected.ShouldDeepEqual(actual);
        }

        [Fact]
        public void TestIndividualOperations()
        {
            var tokens = new List<Token>() {
                new Token(TokenType.NUMBER, 27),
                new Token(TokenType.PLUS),
                new Token(TokenType.NUMBER, 14),
            };
            var expected = new BinNode(
                new NumberNode(27),
                TokenType.PLUS,
                new NumberNode(14)
            );

            var actual = new Parser(tokens).Parse();
            expected.ShouldDeepEqual(actual);

            tokens = new List<Token>() {
                new Token(TokenType.NUMBER, 27),
                new Token(TokenType.MINUS),
                new Token(TokenType.NUMBER, 14),
            };
            expected = new BinNode(
                new NumberNode(27),
                TokenType.MINUS,
                new NumberNode(14)
            );

            actual = new Parser(tokens).Parse();
            expected.ShouldDeepEqual(actual);

            tokens = new List<Token>() {
                new Token(TokenType.NUMBER, 27),
                new Token(TokenType.MULTIPLY),
                new Token(TokenType.NUMBER, 14),
            };
            expected = new BinNode(
                new NumberNode(27),
                TokenType.MULTIPLY,
                new NumberNode(14)
            );

            actual = new Parser(tokens).Parse();
            expected.ShouldDeepEqual(actual);

            tokens = new List<Token>() {
                new Token(TokenType.NUMBER, 27),
                new Token(TokenType.DIVIDE),
                new Token(TokenType.NUMBER, 14),
            };
            expected = new BinNode(
                new NumberNode(27),
                TokenType.DIVIDE,
                new NumberNode(14)
            );

            actual = new Parser(tokens).Parse();
            expected.ShouldDeepEqual(actual);
        }

        [Fact]
        public void TestFullExpression()
        {
            var tokens = new List<Token>() {
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

            var expected = new BinNode(
                new NumberNode(27),
                TokenType.PLUS,
                new BinNode(
                    new BinNode(
                        new BinNode(
                            new NumberNode(43),
                            TokenType.DIVIDE,
                            new NumberNode(36)
                        ),
                        TokenType.MINUS,
                        new NumberNode(48)
                    ),
                    TokenType.MULTIPLY,
                    new NumberNode(51)
                )
            );

            var actual = new Parser(tokens).Parse();
            expected.ShouldDeepEqual(actual);
        }
    }
}
