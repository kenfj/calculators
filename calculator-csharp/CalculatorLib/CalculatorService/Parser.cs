using System;
using System.Linq;
using System.Resources;
using System.Collections.Generic;
using System.Diagnostics.Contracts;
using static System.Globalization.CultureInfo;
using CalculatorService.Nodes;


namespace CalculatorService
{
    /// <summary>Syntax analyzer to build abstract syntax tree</summary>
    public class Parser
    {
        private List<Token>.Enumerator tokens;
        private Token currentToken;
        private TokenType[] PlusMinus = { TokenType.PLUS, TokenType.MINUS };
        private TokenType[] MultiDiv = { TokenType.MULTIPLY, TokenType.DIVIDE };
        private ResourceManager rm;

        public Parser(List<Token> tokenList)
        {
            if (tokenList == null)
                throw new ArgumentNullException(nameof(tokenList));

            tokens = tokenList.GetEnumerator();
            advance();

            rm = new ResourceManager("CalculatorService.Resources", typeof(Parser).Assembly);
        }

        private void advance()
        {
            tokens.MoveNext();
            try { currentToken = tokens.Current; }
            catch (InvalidOperationException) { currentToken = null; }
        }

        public INode Parse()
        {
            if (currentToken != null)
                return expr();
            else
                return null;
        }

        private INode expr()
        {
            var result = term();

            while (currentToken != null)
            {
                if (PlusMinus.Contains(currentToken.TokenType))
                {
                    var operator_ = currentToken.TokenType;
                    advance();
                    result = new BinNode(result, operator_, term());
                }
                else { break; }
            }

            return result;
        }

        private INode term()
        {
            var result = factor();

            while (currentToken != null)
            {
                if (MultiDiv.Contains(currentToken.TokenType))
                {
                    var operator_ = currentToken.TokenType;
                    advance();
                    result = new BinNode(result, operator_, factor());
                }
                else { break; }
            }

            return result;
        }

        private INode factor()
        {
            var token = currentToken;
            var msg = rm.GetString("ErrMsg", InvariantCulture);

            if (token.TokenType == TokenType.LPAREN)
            {
                advance();

                var result = expr();

                if (currentToken.TokenType != TokenType.RPAREN)
                    throw new InvalidOperationException(msg);

                advance();
                return result;
            }

            if (token.TokenType == TokenType.NUMBER)
            {
                advance();
                return new NumberNode(token.Value);
            }
            else if (PlusMinus.Contains(token.TokenType))
            {
                advance();
                return new UnaryNode(token.TokenType, factor());
            }

            throw new InvalidOperationException(msg);
        }
    }
}
