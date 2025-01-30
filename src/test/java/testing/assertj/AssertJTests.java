package testing.assertj;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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

    /**
     * also is doesNotContainExactlyElementsOf
     */
    @Test
    public void assertContainsExactlyElementsOf() {
        List<String> actualList = Arrays.asList("apple", "banana", "cherry");
        List<String> expectedList = Arrays.asList("apple", "banana", "cherry");

        assertThat(actualList).containsExactlyElementsOf(expectedList);
    }

    private void doSomething(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be non-negative");
        }
    }
}
