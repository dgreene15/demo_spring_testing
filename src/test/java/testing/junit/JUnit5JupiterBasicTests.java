package testing.junit;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * JUnit 5 Demo
 * - org.unit.jupiter
 * - org.junit.jupiter.api.function.Executable: functional interface;
 *        - can throw any exception; void execute()
 *        - JUnit handles the exception
 * Assertions.assertThrows(<exception_class>, Executable)
 *
 * @BeforeEach
 * @BeforeAll: runs once per class, has to be static
 */

public class JUnit5JupiterBasicTests {

	/**
	 * assertAll
	 */
	@Test
	public void assertAllTest() {
		String myString = "Hello";

		assertAll(
				() -> assertEquals("Hello", myString),
				() -> assertEquals(5, myString.length())
		);
	}

    @Test
    public void testOptionalOfNull() {
		Assertions.assertThrows(NullPointerException.class, () -> Optional.of(null));
    }

	/**
	 * @ParameterizedTest
	 * - Allows running a single test with different parameters
	 */
	@ParameterizedTest
	@CsvSource({ "1, 1, 2", "2, 3, 5", "4, 5, 9" })
	public void testParameterizedTest(int a, int b, int expected) {
		assertEquals(expected, a + b);
	}

	@Test
	public void testExecutableMessage() {
		JUnit5BasicPojo value = new JUnit5BasicPojo(24);

		assertTrue(true, () -> secondArgumentMethod(value)); //lazy, supplier, only called on error so not called
		assertThat(value.getValue()).isEqualTo(24);

		assertTrue(true, secondArgumentMethod(value));  //will always call print message
		assertThat(value.getValue()).isEqualTo(42);
	}

	@Test
	public void testPerformActionThrowsException() {

		// Assert that the supplier throws the expected exception
		Exception exception = assertThrows(Exception.class, () -> performAction(null));

		// Optionally, verify the exception message
		assertEquals("Input cannot be null", exception.getMessage());
	}

	@Test
	public void testAssumeTrue() {
		boolean runTest = true;
		// Assume that the system property "run.tests" is set to "true"
		assumeTrue(runTest);

		// If the assumption is true, this part of the test will run
		int result = 1 + 1;
		assertEquals(2, result);
	}

	@Test
	public void testWithConditionalChecks() {
		int x = 5;
		int y = 10;

		// This part of the test will always run
		int sum = x + y;
		assertEquals(15, sum);

		// This part of the test will only run if the condition is true
		assumingThat("true".equals(System.getProperty("run.detailed.tests")),
				() -> {
					// Additional checks
					int product = x * y;
					assertEquals(50, product);
				}
		);

		// This part of the test will always run
		int difference = y - x;
		assertEquals(5, difference);
	}

	private String secondArgumentMethod(JUnit5BasicPojo basicPojo) {
		basicPojo.setValue(42);
		return basicPojo.toString();
	}

	private String performAction(String input) throws Exception {
		if (input == null) {
			throw new Exception("Input cannot be null");
		}
		return "Processed " + input;
	}

}

@Data
@AllArgsConstructor
class JUnit5BasicPojo {
	Integer value = 24;
}
