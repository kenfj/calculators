from tokens import TokenType
from dataclasses import dataclass

'''
non-terminals classes
'''


@dataclass
class NumberNode:
    value: float

    def __repr__(self):
        return f"{self.value}"


@dataclass
class BinNode:
    node_a: any
    operator: TokenType
    node_b: any

    def __repr__(self):
        return f"({self.node_a}{self.operator.value}{self.node_b})"


@dataclass
class UnaryNode:
    operator: TokenType
    node: any

    def __repr__(self):
        return f"({self.operator.value}{self.node})"
