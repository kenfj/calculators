using CalculatorService.Nodes;


namespace CalculatorService
{
    /// <summary>Interface for visitor design pattern to visit nodes</summary>
    public interface IVisitor
    {
        Number visit(NumberNode numberNode);
        Number visit(BinNode binNode);
        Number visit(UnaryNode unaryNode);
    }
}
