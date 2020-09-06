from lexer import Lexer
from parser_ import Parser
from interpreter import Interpreter


while True:
    try:
        text = input("calc > ")

        lexer = Lexer(text)
        tokens = lexer.generate_tokens()

        print(list(tokens))  # print for debug

        lexer = Lexer(text)
        tokens = lexer.generate_tokens()

        parser = Parser(tokens)
        tree = parser.parse()

        print(tree)

        if not tree:
            continue

        interpreter = Interpreter()
        number = interpreter.visit(tree)

        print(number.value)

    except EOFError:
        print("Bye")
        break
    except Exception as e:
        print(e)
