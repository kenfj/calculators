# Calculator Library CSharp

* arithmetic expression lexer, parser and interpreter in C#
* calculator implementation: `CalculatorService/Calculator.cs`
  - `Lexer` read input string and generate tokens
  - `Parser` parse tokens and build abstract syntax tree
  - `Interpreter` visit tree nodes and calculate final result
* based on the CodePulse video and Ruslan's Blog plus added some improvements


## Initial Project Setup

* create solution, class library with unit test

```bash
dotnet new sln -o CalculatorLib
cd CalculatorLib

dotnet new classlib -f netcoreapp3.1 -o CalculatorService
dotnet sln add CalculatorService/CalculatorService.csproj

mv CalculatorService/Class1.cs CalculatorService/Calculator.cs

dotnet new xunit -o CalculatorService.Tests
dotnet sln add CalculatorService.Tests/CalculatorService.Tests.csproj

dotnet add CalculatorService.Tests/CalculatorService.Tests.csproj \
    reference CalculatorService/CalculatorService.csproj

mv CalculatorService.Tests/UnitTest1.cs CalculatorService.Tests/CalculatorTest.cs

dotnet add CalculatorService.Tests package MSTest.TestFramework  
dotnet add CalculatorService.Tests package DeepEqual

code .  # start VSCode
```


## Run Test

```bash
# compile resource file to create Resources.resources
resgen Resources.txt

# run unit test
dotnet test
```


## Code Style

```bash
# install code style checker
# https://devblogs.microsoft.com/dotnet/write-better-code-faster-with-roslyn-analyzers/
dotnet add CalculatorService package Microsoft.CodeAnalysis.FxCopAnalyzers

# c.f. StyleCop.Analyzers (legacy static analysis)
# https://medium.com/@michaelparkerdev/linting-c-in-2019-stylecop-sonar-resharper-and-roslyn-73e88af57ebd
# https://stackoverflow.com/questions/43472951
```


## Generate Doc by Doxygen

* c.f. https://qiita.com/ysk24ok/items/5460e65e31d95ec5d9a8

```bash
brew install doxygen
brew install graphviz

# init Doxyfile
doxygen -g

# edit Doxyfile (see also these pages)
# https://stackoverflow.com/a/26244558
# https://qiita.com/et79/items/782a5e29156292c4e461

# generate documents
doxygen

# view documents
python -m http.server 8000 --directory ./html
open http://localhost:8000/
```


## Reference

* pls refer my repository `csharp-starter`
* How to Build a Calculator
 - https://gaufung.com/post/ji-zhu/how-to-build-a-calculator
* Simple Math Interpreter in Python (CodePulse)
  - https://www.youtube.com/watch?v=88lmIMHhYNs&list=PLZQftyCk7_Sdu5BFaXB_jLeJ9C78si5_3
  - https://github.com/davidcallanan/py-simple-math-interpreter/
