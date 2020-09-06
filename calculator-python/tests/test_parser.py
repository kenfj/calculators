from tokens import Token, TokenType
from parser_ import Parser
from nodes import BinNode, NumberNode, UnaryNode


def test_empty():
    tokens = []
    node = Parser(tokens).parse()
    assert node is None


def test_number():
    tokens = [Token(TokenType.NUMBER, 51.2)]
    node = Parser(tokens).parse()
    assert node == NumberNode(51.2)


def test_plus():
    tokens = [
        Token(TokenType.NUMBER, 1),
        Token(TokenType.PLUS),
        Token(TokenType.NUMBER, 2),
    ]

    node = Parser(tokens).parse()
    assert node == BinNode(NumberNode(1), TokenType.PLUS, NumberNode(2))


def test_unary():
    tokens = [
        Token(TokenType.PLUS),
        Token(TokenType.NUMBER, 1),
    ]

    node = Parser(tokens).parse()
    assert node == UnaryNode(TokenType.PLUS, NumberNode(1))

    tokens = [
        Token(TokenType.MINUS),
        Token(TokenType.NUMBER, 1),
    ]

    node = Parser(tokens).parse()
    assert node == UnaryNode(TokenType.MINUS, NumberNode(1))


def test_individual_operations():
    tokens = [
        Token(TokenType.NUMBER, 27),
        Token(TokenType.PLUS),
        Token(TokenType.NUMBER, 14),
    ]

    node = Parser(tokens).parse()
    assert node == BinNode(NumberNode(27), TokenType.PLUS, NumberNode(14))

    tokens = [
        Token(TokenType.NUMBER, 27),
        Token(TokenType.MINUS),
        Token(TokenType.NUMBER, 14),
    ]

    node = Parser(tokens).parse()
    assert node == BinNode(NumberNode(27), TokenType.MINUS, NumberNode(14))

    tokens = [
        Token(TokenType.NUMBER, 27),
        Token(TokenType.MULTIPLY),
        Token(TokenType.NUMBER, 14),
    ]

    node = Parser(tokens).parse()
    assert node == BinNode(NumberNode(27), TokenType.MULTIPLY, NumberNode(14))

    tokens = [
        Token(TokenType.NUMBER, 27),
        Token(TokenType.DIVIDE),
        Token(TokenType.NUMBER, 14),
    ]

    node = Parser(tokens).parse()
    assert node == BinNode(NumberNode(27), TokenType.DIVIDE, NumberNode(14))


def test_full_expression():
    # 27 + (43 / 36 - 48) * 51
    tokens = [
        Token(TokenType.NUMBER, 27),
        Token(TokenType.PLUS),
        Token(TokenType.LPAREN),
        Token(TokenType.NUMBER, 43),
        Token(TokenType.DIVIDE),
        Token(TokenType.NUMBER, 36),
        Token(TokenType.MINUS),
        Token(TokenType.NUMBER, 48),
        Token(TokenType.RPAREN),
        Token(TokenType.MULTIPLY),
        Token(TokenType.NUMBER, 51),
    ]

    node = Parser(tokens).parse()

    assert node == BinNode(
        NumberNode(27),
        TokenType.PLUS,
        BinNode(
            BinNode(
                BinNode(
                    NumberNode(43),
                    TokenType.DIVIDE,
                    NumberNode(36),
                ),
                TokenType.MINUS,
                NumberNode(48),
            ),
            TokenType.MULTIPLY,
            NumberNode(51),
        )
    )
