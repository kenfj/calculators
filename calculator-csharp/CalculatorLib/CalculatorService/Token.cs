using System;
using System.Collections.Generic;


namespace CalculatorService
{
    /// <summary>Type of tokens (Enum equivalent class)</summary>
    /// <see>
    /// c.f. https://stackoverflow.com/questions/630803
    /// </see>
    public class TokenType
    {
        public string Value { get; }

        public static readonly TokenType NUMBER = new TokenType("0123456789.");
        public static readonly TokenType PLUS = new TokenType("+");
        public static readonly TokenType MINUS = new TokenType("-");
        public static readonly TokenType MULTIPLY = new TokenType("*");
        public static readonly TokenType DIVIDE = new TokenType("/");
        public static readonly TokenType LPAREN = new TokenType("(");
        public static readonly TokenType RPAREN = new TokenType(")");

        public static readonly Dictionary<char, TokenType> Map = new Dictionary<char, TokenType>() {
            { '+', PLUS },
            { '-', MINUS },
            { '*', MULTIPLY },
            { '/', DIVIDE },
            { '(', LPAREN },
            { ')', RPAREN },
        };

        public TokenType(string value)
        {
            Value = value;
        }

        public static string GetSymbols()
        {
            return "+-*/()";
        }
    }

    /// <summary>Unit of literal to parse and interpret</summary>
    public class Token
    {
        public TokenType TokenType { get; set; }
        public double Value { get; set; }

        /// <summary>Represent symbols e.g. new Token(TokenType.PLUS)</summary>
        public Token(TokenType tokenType)
        {
            TokenType = tokenType;
        }

        /// <summary>Represent numbers e.g. new Token(TokenType.NUMBER, 123)</summary>
        public Token(TokenType tokenType, double value)
        {
            TokenType = tokenType;
            Value = value;
        }
    }
}
