# Calculator Lexer and Parser

* Calculator: simple interpreter of arithmetic expressions
* calculator implementation: `src/main.py`
  - `lexer` read input string and generate tokens
  - `parser` parse tokens and build abstract syntax tree
  - `interpreter` visit tree nodes and calculate final result
* based on the CodePulse video and Ruslan's Blog plus added some improvements


## Setup

```bash
# initial setup commands for reference
# export PIPENV_VENV_IN_PROJECT=true
# pipenv --python 3.8
# pipenv install --dev pylint autopep8 rope pytest pytest-cov
# pipenv install ply

pipenv install
pipenv install --dev
pipenv shell
```


## Run

* run from VSCode or from terminal as below

```bash
# run unit tests
pytest tests

# print more info
pytest tests -v
# with coverage report
pytest tests -v --cov=src --cov-report=html --cov-report=term-missing

# run calculator
python src/main.py
```


## Upgrade

```bash
# package upgrade
pipenv update --outdated            # check
pipenv update                       # upgrade
pipenv install --dev pytest==4.6.2  # install specific version

# re-create virtual environment
pipenv --rm
pipenv install
pipenv install --dev
```


## Reference

* Simple Math Interpreter in Python (CodePulse)
  - https://www.youtube.com/watch?v=88lmIMHhYNs&list=PLZQftyCk7_Sdu5BFaXB_jLeJ9C78si5_3
  - https://github.com/davidcallanan/py-simple-math-interpreter/
* Let’s Build A Simple Interpreter (Ruslan's Blog)
  - https://ruslanspivak.com/lsbasi-part7/
* How to Build a Calculator (C#)
 - https://gaufung.com/post/ji-zhu/how-to-build-a-calculator
