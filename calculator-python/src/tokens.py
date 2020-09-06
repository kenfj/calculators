from enum import Enum
from typing import List
from dataclasses import dataclass


class TokenType(Enum):
    NUMBER = "0123456789."
    PLUS = '+'
    MINUS = '-'
    MULTIPLY = '*'
    DIVIDE = '/'
    LPAREN = '('
    RPAREN = ')'

    def __str__(self):
        return self.name

    @classmethod
    def values(cls) -> List[str]:
        return list(map(lambda c: c.value, cls))

    # @classmethod
    # def operators(cls):
    #     operators_ = [cls.PLUS, cls.PLUS, cls.PLUS, cls.PLUS]
    #     return list(map(lambda x: x.value, operators_))

    @classmethod
    def symbols(cls) -> List[str]:
        return [x for x in cls.values() if x != TokenType.NUMBER.value]


@dataclass
class Token:
    type: TokenType
    value: any = None

    def __repr__(self):
        return self.type.name + (f":{self.value}" if self.value else "")
