using System;
using Xunit;

namespace CalculatorService.Tests
{
    public class CalculatorTest
    {
        [Fact]
        public void BasicTest()
        {
            var res = Calculator.Calc("1");
            Assert.Equal(1, res.value);

            res = Calculator.Calc("1 * (2 + 3) + 4 * 5");
            Assert.Equal(25, res.value);

            res = Calculator.Calc("-1");
            Assert.Equal(-1, res.value);

            res = Calculator.Calc("- 1 * ((2 + 3) + 4 * (5 - 6)) + 7");
            Assert.Equal(6, res.value);

            res = Calculator.Calc("1 / 0"); // returns Infinity ∞ in C#
            Assert.Equal(Double.PositiveInfinity, res.value);
        }
    }
}
