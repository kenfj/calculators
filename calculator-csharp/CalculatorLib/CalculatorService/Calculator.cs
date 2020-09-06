using System;
using System.Linq;


namespace CalculatorService
{
    /// <summary>Main class to calculate arithmetic expression</summary>
    public static class Calculator
    {
        public static Number Calc(string text)
        {
            var lexer = new Lexer(text);
            var tokens = lexer.GenerateTokens();

            var parser = new Parser(tokens.ToList());
            var tree = parser.Parse();

            var interpreter = new Interpreter();

            return tree.accept(interpreter);
        }
    }
}
