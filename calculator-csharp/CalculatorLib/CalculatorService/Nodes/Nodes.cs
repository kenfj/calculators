using System;
using System.Diagnostics.Contracts;


namespace CalculatorService.Nodes
{
    /// <summary>AST node interface</summary>
    public interface INode
    {
        Number accept(IVisitor visitor);
    }

    /// <summary>Represent number in AST</summary>
    public class NumberNode : INode
    {
        public double Value { get; set; }

        public NumberNode(double value)
        {
            Value = value;
        }

        public Number accept(IVisitor visitor)
        {
            Contract.Requires(visitor != null);

            return visitor.visit(this);
        }
    }

    /// <summary>Represent operation of two nodes in AST</summary>
    public class BinNode : INode
    {
        public INode nodeA { get; set; }
        public TokenType operative { get; set; }
        public INode nodeB { get; set; }

        public BinNode(INode nodeA, TokenType operative, INode nodeB)
        {
            this.nodeA = nodeA;
            this.operative = operative;
            this.nodeB = nodeB;
        }

        public Number accept(IVisitor visitor)
        {
            Contract.Requires(visitor != null);

            return visitor.visit(this);
        }
    }

    /// <summary>Represent unary operator (+, -) in AST</summary>
    public class UnaryNode : INode
    {
        public TokenType operative { get; set; }
        public INode node { get; set; }

        public UnaryNode(TokenType tokenType, INode node)
        {
            operative = tokenType;
            this.node = node;
        }

        public Number accept(IVisitor visitor)
        {
            Contract.Requires(visitor != null);

            return visitor.visit(this);
        }
    }
}
