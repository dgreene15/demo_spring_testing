package testing.assertj;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * AssertJ
 * Helps to write fluent and readable assertions in Java tests
 * .usingRecursiveComparison() #compare objects deeply with nested fields
 * .withEqualsForType() specify a custom equality comparison for type
 * .isEqualTo()
 */
public class AssertJTests {

    @Test
    public void assertThatListTests() {
        List<String> result = Arrays.asList("Hello", "World");

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .contains("Hello", "World")
                .containsExactly("Hello", "World")
                .doesNotContain("Goodbye")
                .hasSize(2);
    }

    @Test
    public void assertThatExceptionOfTypeTests() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> doSomething(-1))
                .withMessage("Value must be non-negative")
                .withMessageContaining("non-negative")
                .withNoCause();

        // withCauseExactlyInstanceOf: check if caused by another exception
    }

    @Test
    public void testNoExceptions() {
        assertThatCode(() -> {
            // Code that should not throw any exceptions
            int result = 10 / 2; // Example of code that won't throw an exception
            System.out.println("Result: " + result);
        }).doesNotThrowAnyException();

        // also
        //.isInstanceOf(ArithmeticException.class); //Assert that the correct exception is thrown.
        // .doesNotThrowAnyException();
        // .isInstanceOf(NullPointerException.class);
    }

    /**
     * also is doesNotContainExactlyElementsOf
     */
    @Test
    public void assertContainsExactlyElementsOf() {
        List<String> actualList = Arrays.asList("apple", "banana", "cherry");
        List<String> expectedList = Arrays.asList("apple", "banana", "cherry");

        assertThat(actualList).containsExactlyElementsOf(expectedList);
    }

    @Test
    public void assertNotBlank() {
        String str = "Hello";
        assertThat(str).isNotBlank();
    }

    @Test
    public void assertContainsEntry() {
        Map<Integer, String> map = Map.of(0, "apple", 1, "banana", 2, "cherry");
        assertThat(map).containsEntry(0, "apple");
        assertThat(map).containsOnlyKeys(0, 1, 2);
    }

    private void doSomething(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be non-negative");
        }
    }
}
