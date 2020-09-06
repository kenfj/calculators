using System;
using System.Diagnostics.Contracts;
using CalculatorService.Nodes;


namespace CalculatorService
{
    /// <summary>Visit tree nodes and calculate final result</summary>
    public class Interpreter : IVisitor
    {
        public Number visit(NumberNode node)
        {
            Contract.Requires(node != null);

            return new Number(node.Value);
        }

        public Number visit(BinNode node)
        {
            Contract.Requires(node != null);

            var left = node.nodeA.accept(this).value;
            var right = node.nodeB.accept(this).value;
            double value;

            if (node.operative == TokenType.PLUS)
                value = left + right;
            else if (node.operative == TokenType.MINUS)
                value = left - right;
            else if (node.operative == TokenType.MULTIPLY)
                value = left * right;
            else if (node.operative == TokenType.DIVIDE)
                value = left / right;
            else
                throw new Exception($"Invalid operator {node.operative}");

            return new Number(value);
        }

        public Number visit(UnaryNode node)
        {
            Contract.Requires(node != null);

            var value = node.node.accept(this).value;

            if (node.operative == TokenType.MINUS)
                value = -1 * value;

            return new Number(value);
        }
    }
}
