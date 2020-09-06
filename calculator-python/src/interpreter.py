from values import Number
from tokens import TokenType


class Interpreter:
    def visit(self, node):
        method_name = f"visit_{type(node).__name__}"
        method = getattr(self, method_name)
        return method(node)

    def visit_NumberNode(self, node):
        return Number(node.value)

    def visit_BinNode(self, node):
        '''AST DFS postorder traversal'''
        left = self.visit(node.node_a).value
        right = self.visit(node.node_b).value

        if node.operator == TokenType.PLUS:
            value = left + right
        if node.operator == TokenType.MINUS:
            value = left - right
        if node.operator == TokenType.MULTIPLY:
            value = left * right
        if node.operator == TokenType.DIVIDE:
            try:
                value = left / right
            except:
                raise Exception("Runtime math error")

        return Number(value)

    def visit_UnaryNode(self, node):
        value = self.visit(node.node).value

        if node.operator == TokenType.PLUS:
            value = + value
        if node.operator == TokenType.MINUS:
            value = - value

        return Number(value)
