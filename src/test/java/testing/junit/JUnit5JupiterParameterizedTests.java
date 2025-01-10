package testing.junit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 // @ParameterizedTest
 // @ValueSource
 //@CsvSource
 // @CsvFileSource
 // @NullSource
 // @EmptySource
 // @NullAndEmptySource
 // @EnumSource(*.class)
 / @EnumSourec(*.class, names=["enum1", "enum2"])
 // @MethodSource
 */
public class JUnit5JupiterParameterizedTests {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testValueSource(int num) {
        System.out.println("Num: "+ num);
    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3", "4, 5, 6"})
    public void testParameterizedCSV(int a, int b, int result) {
        Calculator cal = new Calculator();
        int calResult = cal.add(a, b);
        System.out.println("a: " + a + "; " + "b: " + b + "; Result: " + calResult + "; Expected: " + result);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            1, 2, 3
            2, 3, 5
            4, 5, 9
            5, 6, 11
            """)
    public void testAdd(int a, int b, int expected) {
        Calculator cal = new Calculator();
        assertEquals(expected, cal.add(a, b));
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void testWithMethodSource(String input, int expected) {
        assertEquals(expected, input.length());
    }

    @ParameterizedTest
    @NullSource
    public void testIsEmptyWithNull(String input) {
        // Verify that isEmpty returns true for null input
        assertNull(input);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10})
    public void testIsEven(int number) {
        // Verify that the number is even
        assertTrue(JUnit5JupiterParameterizedTests.isEven(number));
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of("apple", 5),
                Arguments.of("banana", 6),
                Arguments.of("cherry", 6)
        );
    }

}

class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}
