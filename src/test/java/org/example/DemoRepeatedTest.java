package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DemoRepeatedTest {

    Calculator calculator;

    @BeforeEach
    void beforeEach() {
        calculator = new Calculator();
        System.out.println("@BeforeEach");
    }

    @DisplayName("Test 4 / 2 = 2")
    @RepeatedTest(value = 3, name = "{displayName}. Repetition {currentRepetition} of {totalRepetitions}")
    void testDivideInteger_WhenFourIsDividedByTwo_ShouldReturnTwo(RepetitionInfo repetitionInfo,
                                                                  TestInfo testInfo) {
        System.out.println("Running " + testInfo.getTestMethod().get().getName());
        System.out.println("Repetition #" + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
        // Arrange // Given
        int dividend = 4;
        int divisor = 2;
        int expected = 2;

        // Act // When
        int actual = calculator.divideInteger(dividend, divisor);

        // Assert // Then
        assertEquals(expected, actual);
    }

}
