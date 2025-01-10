package testing.junit;

import java.util.Optional;

import org.junit.Test;

/**
 * 
 * JUnit 4 Test Demo
 * - org.junit
 * - Requires setting the test runner to version 4
 * - @Before
 * - @BeforeClass
 */
public class JUnit4Tests {
	
	@Test(expected = NullPointerException.class)
	public void testOptionalOfNull() {
		Optional.of(null);
	}
	
}
