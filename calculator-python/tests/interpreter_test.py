import pytest

from nodes import BinNode, NumberNode, UnaryNode
from interpreter import Interpreter
from values import Number
from tokens import TokenType


def test_numbers():
    value = Interpreter().visit(NumberNode(51.2))
    assert value == Number(51.2)


def test_unary():
    node = UnaryNode(TokenType.PLUS, NumberNode(1))
    value = Interpreter().visit(node)
    assert value == Number(1)

    node = UnaryNode(TokenType.MINUS, NumberNode(1))
    value = Interpreter().visit(node)
    assert value == Number(-1)


def test_individual_operations():
    left = NumberNode(27)
    right = NumberNode(14)

    value = Interpreter().visit(BinNode(left, TokenType.PLUS, right))
    assert value == Number(41)

    value = Interpreter().visit(BinNode(left, TokenType.MINUS, right))
    assert value == Number(13)

    value = Interpreter().visit(BinNode(left, TokenType.MULTIPLY, right))
    assert value == Number(378)

    value = Interpreter().visit(BinNode(left, TokenType.DIVIDE, right))
    assert value.value == pytest.approx(1.92857, 5)

    with pytest.raises(Exception):
        Interpreter().visit(BinNode(left, TokenType.DIVIDE, NumberNode(0)))


def test_full_expression():
    tree = BinNode(
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

    result = Interpreter().visit(tree)
    assert result.value == pytest.approx(-2360.08, 2)
