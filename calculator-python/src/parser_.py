from tokens import TokenType
from nodes import NumberNode, BinNode, UnaryNode


class Parser:
    '''
    syntax analyzer to build abstract syntax tree

    [terminals] Number class
    [non-terminals] Node classes (sequence of Number and/or Node)

    [left-associative] apply left first when operand have operators two side
        example: 1 + 2 + 3 is (1 + 2) + 3
    [precedence of operators] *,/ have higher precedence than +,-

    when N levels of precedence, you will need N + 1 non-terminals
    [non-terminals precedence] Expression (+,-) < Term (*,/) < Factor (number)

    [Grammer Rules]
    expr: term ((PLUS|MINUS)term)* | '(' expr ')'
    term: factor ((MUL|DIV)factor)*
    factor: (PLUS|MINUS) factor | number | LPAREN expr RPAREN

    // c.f. this is simple example for x*y or x/y
    // expr: factor ((MUL|DIV) factor)*
    // factor: number

    [Grammar Notation]
    | - Alternatives -> if/elif/else statement
    () - grouping of terminals and/or non-terminals
    ()* - zero or more times (optional) -> while loop

    [AST examples]
      1+2*3:        +  (expr)
                  1   *  (term)
                     2 3  (number)

      (1+2)*3:      *
                  +   3
                 1 2
    '''

    def __init__(self, tokens):
        self.tokens = iter(tokens)
        self._advance()

    def _advance(self):
        try:
            self.current_token = next(self.tokens)
        except StopIteration:
            self.current_token = None

    def parse(self):
        return self.expr() if self.current_token else None

    def expr(self):
        '''expr: term ((PLUS|MINUS)term)*'''
        result = self.term()

        while self.current_token:
            if self.current_token.type in [TokenType.PLUS, TokenType.MINUS]:
                operator_ = self.current_token.type
                self._advance()
                result = BinNode(result, operator_, self.term())
            else:
                break

        return result

    def term(self):
        '''term: factor ((MUL|DIV)factor)*'''
        result = self.factor()

        while self.current_token:
            if self.current_token.type in [TokenType.MULTIPLY, TokenType.DIVIDE]:
                operator_ = self.current_token.type
                self._advance()
                result = BinNode(result, operator_, self.factor())
            else:
                break

        return result

    def factor(self):
        '''factor: number | LPAREN expr RPAREN'''
        token = self.current_token

        # for parentheses
        if token.type == TokenType.LPAREN:
            self._advance()

            # recursive call to restart from expression
            result = self.expr()

            if self.current_token.type != TokenType.RPAREN:
                self.raise_error()

            self._advance()
            return result

        # for simple number
        if token.type == TokenType.NUMBER:
            self._advance()
            return NumberNode(token.value)

        # for unary operations
        elif token.type in [TokenType.PLUS, TokenType.MINUS]:
            self._advance()
            return UnaryNode(token.type, self.factor())

        self.raise_error()

    def raise_error(self):
        raise Exception("Invalid syntax")
