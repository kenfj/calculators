using Xunit;
using DeepEqual.Syntax;

using CalculatorService.Nodes;


namespace CalculatorService.Tests
{
    public class InterpreterTest
    {
        [Fact]
        public void TestEmpty()
        {
            var node = new NumberNode(51.2);
            var actual = new Interpreter().visit(node);
            var expected = new Number(51.2);

            expected.ShouldDeepEqual(actual);
        }

        [Fact]
        public void TestUnary()
        {
            var node = new UnaryNode(TokenType.PLUS, new NumberNode(1));
            var actual = new Interpreter().visit(node);
            var expected = new Number(1);

            expected.ShouldDeepEqual(actual);

            node = new UnaryNode(TokenType.MINUS, new NumberNode(1));
            actual = new Interpreter().visit(node);
            expected = new Number(-1);

            expected.ShouldDeepEqual(actual);
        }

        [Fact]
        public void TestIndividualOperations()
        {
            var left = new NumberNode(27);
            var right = new NumberNode(14);

            var node = new BinNode(left, TokenType.PLUS, right);
            var actual = new Interpreter().visit(node);
            var expected = new Number(41);

            expected.ShouldDeepEqual(actual);

            node = new BinNode(left, TokenType.MINUS, right);
            actual = new Interpreter().visit(node);
            expected = new Number(13);

            expected.ShouldDeepEqual(actual);

            node = new BinNode(left, TokenType.MULTIPLY, right);
            actual = new Interpreter().visit(node);
            expected = new Number(378);

            expected.ShouldDeepEqual(actual);

            node = new BinNode(left, TokenType.DIVIDE, right);
            actual = new Interpreter().visit(node);
            expected = new Number(1.9285714285714286);

            expected.ShouldDeepEqual(actual);
        }

        [Fact]
        public void TestFullExpression()
        {
            var node = new BinNode(
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

            var actual = new Interpreter().visit(node);
            var expected = new Number(-2360.0833333333335);

            expected.ShouldDeepEqual(actual);
        }
    }
}
