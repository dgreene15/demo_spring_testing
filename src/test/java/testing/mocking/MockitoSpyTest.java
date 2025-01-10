package testing.mocking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * spy: calls actual methods and allows to check behavior
 */
@ExtendWith(MockitoExtension.class)
public class MockitoSpyTest {

    @Spy
    SpyGreeter greeterSpy;

    @Test
    void testSpy() {
        // Stub the greet method
        doReturn("Mocked Hello, John").when(greeterSpy).greet("John");

        // Call the real greetUppercase method
        String result = greeterSpy.greetUppercase("John");

        // Verify that the result is as expected
        assertEquals("MOCKED HELLO, JOHN", result);

        // Verify that the greet method was called
        verify(greeterSpy).greet("John");

    }
}

class SpyGreeter {

    public String greet(String name) {
        return "Hello, " + name;
    }

    public String greetUppercase(String name) {
        return greet(name).toUpperCase();
    }
}
