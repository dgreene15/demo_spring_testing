package testing.junit;

import org.junit.jupiter.api.extension.ExtensionContext;
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
        assertTrue(num > 0 && num < 4);
    }

    @ParameterizedTest
    @CsvSource({"1, 2, 3", "4, 5, 9"})
    public void testParameterizedCSV(int a, int b, int result) {
        Calculator cal = new Calculator();
        int calResult = cal.add(a, b);

        assertEquals(result, calResult);
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

    @ParameterizedTest
    @EnumSource(DayOfWeek.class) // This will pass all enum constants as arguments
    void testDayOfWeek(DayOfWeek day) {
        assertNotNull(day);
    }

    @ParameterizedTest
    @ArgumentsSource(CustomArgumentsProvider.class) // Use the custom ArgumentsProvider
    void testWithCustomArguments(int id, String name) {
        // Test logic: Verify that the name corresponds to the correct ID
        switch (id) {
            case 1:
                assertEquals("apple", name);
                break;
            case 2:
                assertEquals("banana", name);
                break;
            case 3:
                assertEquals("cherry", name);
                break;
            default:
                throw new IllegalArgumentException("Invalid ID: " + id);
        }
    }

}

class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}

enum DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

class CustomArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(1, "apple"),
                Arguments.of(2, "banana"),
                Arguments.of(3, "cherry")
        );
    }
}
