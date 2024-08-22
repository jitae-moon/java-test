package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

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

    @DisplayName("Test Integer Subtraction [minuend, subtrahend, expected]")
    @ParameterizedTest
//    @MethodSource("subtractIntegerInputParameters")
//    @MethodSource // Same as method name
//    @CsvSource({
//            "33, 1, 32",
//            "200, 100, 100",
//            "321, 33, 288"
//    })
    @CsvFileSource(resources = "/integer.csv")
    void testSubtraction(int minuend, int subtrahend, int expected) {
        int actual = calculator.subtractInteger(minuend, subtrahend);
        assertEquals(expected, actual, () -> minuend + " - " + subtrahend + "is not " + actual);
    }

//    private static Stream<Arguments> subtractIntegerInputParameters() {
    private static Stream<Arguments> testSubtraction() {
        return Stream.of(
                Arguments.of(33, 1, 32),
                Arguments.of(34, 2, 32),
                Arguments.of(100, 1000, -900),
                Arguments.of(5, 34, -29),
                Arguments.of(35, 9, 26)
        );
    }

    @ValueSource(strings = {"Jason", "John", "Emma"})
    @ParameterizedTest
    void valueSourceDemonstration(String firstName) {
        System.out.println(firstName);
        assertNotNull(firstName);
    }

}
