package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math operations in Calculator class")
class CalculatorTest {

    Calculator calculator;

    @BeforeAll
    static void setup() {
        System.out.println("Executing @BeforeAll");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("Executing @AfterAll");
    }

    @BeforeEach
    void beforeEach() {
        calculator = new Calculator();
        System.out.println("Executing @BeforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("Executing @AfterEach");
    }

    // test<System Under Test>_<Condition or State Change>_<Expected Result>
    @DisplayName("Test 4 / 2 = 2")
    @Test
    void testDivideInteger_WhenFourIsDividedByTwo_ShouldReturnTwo() {
        // Arrange // Given
        int dividend = 4;
        int divisor = 2;
        int expected = 2;

        // Act // When
        int actual = calculator.divideInteger(dividend, divisor);

        // Assert // Then
        assertEquals(expected, actual);
    }

//    @Disabled("TODO")
    @DisplayName("Division by zero")
    @Test
    void testDivideInteger_WhenDividedByZero_ShouldThrowArithmeticException() {
        // Given
        int dividend = 4;
        int divisor = 0;
        String expected = "/ by zero";

        // When
        ArithmeticException actual = assertThrows(ArithmeticException.class, () -> {
            calculator.divideInteger(dividend, divisor);
        }, "Division by zero throws ArithmeticException");

        // Then
        assertEquals(expected, actual.getMessage(), "Unexpected Exception message");
    }

    @DisplayName("Test 5 -1 = 4")
    @Test
    void testSubstraction() {
        int minuend = 5;
        int subtrachend = 1;
        int expected = 4;
        int actual = calculator.substractInteger(5, 1);
        assertEquals(expected, actual, () -> minuend + " - " + subtrachend + "is not " + actual);
    }

}