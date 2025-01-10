package testing.mocking;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Mockito Argument Matchers
 * anyString()
 * anyInt()
 * any(Object.class)
 * eq("value")
 * anyBoolean()
 * anyList()
 * any(byte[].class)
 * any(Object[].class)
 */
public class MockitoArgumentMatcherTests {

    @Test
    public void testArgumentMatcher() {
        Foo mockFoo = mock(Foo.class);
        when(mockFoo.fooMethod1(anyString(), anyInt(), any(Object.class))).thenReturn(true);

        assertTrue(mockFoo.fooMethod1("A", 1, "A"));
        assertTrue(mockFoo.fooMethod1("B", 10, new Object()));
    }

    @Test
    public void testArgumentMatcherMix() {
        Foo mockFoo = mock(Foo.class);
        when(mockFoo.fooMethod1(Mockito.contains("test"), Mockito.eq(1), any(Object.class))).thenReturn(true);

        assertTrue(mockFoo.fooMethod1("test", 1, "A"));
        assertTrue(mockFoo.fooMethod1("test", 1, new Object()));
    }

}

class Foo {
    boolean fooMethod1(String str, int i, Object obj) {
        return false;
    }
}
