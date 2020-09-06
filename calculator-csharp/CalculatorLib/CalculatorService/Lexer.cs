using System;
using System.Resources;
using System.Linq;
using System.Collections.Generic;
using static System.Globalization.NumberFormatInfo;

# pragma warning disable CA1307 // Specify StringComparison


namespace CalculatorService
{
    /// <summary>Lexical analyzer (tokenizer)</summary>
    public class Lexer
    {
        private static string WHITESPACE = " \n\t";
        private static string NUMBER = TokenType.NUMBER.Value;
        private static string SYMBOLS = TokenType.GetSymbols();

        private CharEnumerator chars;
        private char? currentChar;

        public Lexer(string text)
        {
            text = text ?? "";

            chars = text.GetEnumerator();
            advance();
        }

        private void advance()
        {
            chars.MoveNext();
            try { currentChar = chars.Current; }
            catch (InvalidOperationException) { currentChar = null; }
        }

        public IEnumerable<Token> GenerateTokens()
        {
            while (currentChar != null)
            {
                if (WHITESPACE.Contains(chars.Current))
                    advance();
                else if (NUMBER.Contains(chars.Current))
                    yield return generateNumber();
                else if (SYMBOLS.Contains(chars.Current))
                    yield return generateSymbol();
                else
                    throw new Exception($"Illegal Char {currentChar}");
            }
        }

        private Token generateNumber()
        {
            string numberStr = "";

            while (currentChar != null && NUMBER.Contains(currentChar.ToString()))
            {
                numberStr += currentChar;
                advance();
            }

            if (numberStr.Count(x => x == '.') > 1)
                throw new Exception($"too many dot in {numberStr}");
            if (numberStr.EndsWith("."))
                numberStr += "0";

            double value = double.Parse(numberStr, provider: InvariantInfo);
            return new Token(TokenType.NUMBER, value);
        }

        private Token generateSymbol()
        {
            var tokenType = TokenType.Map[chars.Current];
            advance();
            return new Token(tokenType);
        }
    }
}
