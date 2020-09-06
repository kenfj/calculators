from tokens import Token, TokenType

WHITESPACE = ' \n\t'
NUMBER = TokenType.NUMBER.value
SYMBOLS = TokenType.symbols()


class Lexer:
    '''lexical analyzer (tokenizer)'''

    def __init__(self, _text):
        self.text = iter(_text)
        self._advance()

    def _advance(self):
        try:
            self.current_char = next(self.text)
        except StopIteration:
            self.current_char = None

    def generate_tokens(self):
        while self.current_char:
            if self.current_char in WHITESPACE:
                self._advance()
            elif self.current_char in NUMBER:
                yield self._generate_number()
            elif self.current_char in SYMBOLS:
                yield self._generate_symbol()
            else:
                raise Exception(f"Illegal Char {self.current_char}")

    def _generate_number(self):
        number_str = ""

        while self.current_char:
            if self.current_char not in NUMBER:
                break

            number_str += self.current_char
            self._advance()

        if number_str.count(".") > 1:
            raise Exception("too many dot")
        if number_str.startswith('.'):
            number_str = '0' + number_str
        if number_str.endswith('.'):
            number_str += '0'

        return Token(TokenType.NUMBER, float(number_str))

    def _generate_symbol(self):
        token_type = TokenType(self.current_char)
        self._advance()

        return Token(token_type)
