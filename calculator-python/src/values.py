from dataclasses import dataclass

'''
terminals class
'''


@dataclass
class Number:
    '''terminal class'''
    value: float

    def __repr__(self):
        return f"{self.value}"
