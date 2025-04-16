package testing.mocking;

import com.example.demo.utilities.MapStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Mockito
 * ---
 * 1) Set expectations when().thenReturn
 * 2) Call method under test
 * 3) Check
 * 4) Verify stub called
 * ---
 * Mockito stub is same as mock but without the verify methods
 * ---
 * when()
 * thenReturn()
 * verify()
 * when().thenThrow()
 * assertThatExceptionOfType().isThrownBy()
 * ---
 * verify: use to verify  behavior on a mock
 *     Mockito.verify(mock)
 *     Mockito.verify(mock, VerificationMode)
 *     Mockito.verify(mock, VerificationMode).<method with argument matchers>
 *        ---- Object
 *      Mockito.eq(value) - equal to value
 *      Mockito.same(value) - same object
 *      Mockito.isA(value) - type check
 *      Mockito.refEq(value) - can compare object using fields verse equals
 *      --- String
 *      Mockito.contains
 *      Mockito.startsWith
 *      Mockito.endsWith
 *      Mockito.matches
 *      ---
 *      Mockito.isNull()
 *      Mockito.isNotNull()
 *      times(x)
 *  - ArgumentMatchers have to be used for all arguments
 */
@ExtendWith(MockitoExtension.class)
public class MockitoBasicTests {

    @Mock
    Map<String, String> mapMock;

    /**
     * Map will get injected into the MapStorage
     */
    @InjectMocks
    MapStorage mapStorage = new MapStorage();

    /**
     * Mockito.when(<Mock Obj>.<method call>).thenReturn(<value>)
     */
    @Test
    public void testBasicMock() {
        /*
            Internal map for MapStorage is mocked so this will mock getting
            a value from the map.
         */
        String mapKeyValue = "myKey";
        Mockito.when(mapMock.get(mapKeyValue)).thenReturn("myValue");

        assertThat(mapStorage.getValue(mapKeyValue)).isEqualTo("myValue");

        verify(mapMock).get(mapKeyValue);  //verifies map called with specific key passed
        verify(mapMock).get(Mockito.eq(mapKeyValue));
        verify(mapMock).get(Mockito.same(mapKeyValue)); // check same object
        verify(mapMock).get(anyString());
        verify(mapMock, atMost(1)).get(mapKeyValue);
        verify(mapMock, atLeast(1)).get(mapKeyValue);
        verify(mapMock, never()).size();    // verifies size() was not called on map mock

    }

    /*
     * Mockito.when(<Mock Obj>.<method call>).thenReturn(<value1>, <value2>, <value3>)
     */
    @Test
    public void testBasicMockMultipleReturnValues() {
        /*
            Internal map for MapStorage is mocked so this will mock getting
            a value from the map.
         */
        String mapKeyValue = "myKey";
        Mockito.when(mapMock.get(mapKeyValue)).thenReturn("myValue1", "myValue2", "myValue3");

        assertThat(mapStorage.getValue(mapKeyValue)).isEqualTo("myValue1");
        assertThat(mapStorage.getValue(mapKeyValue)).isEqualTo("myValue2");
        assertThat(mapStorage.getValue(mapKeyValue)).isEqualTo("myValue3");

        verify(mapMock, times(3)).get(mapKeyValue);
        verify(mapMock, atMost(3)).get(mapKeyValue);
        verify(mapMock, atLeast(1)).get(mapKeyValue);
        verify(mapMock, never()).size();    // verifies size() was not called on map mock
    }

    /*
     * Mockito.when(<Mock Obj>.<method call>).thenReturn(<value1>).thenReturn(<value2>).thenReturn(<value3>)
     */
    @Test
    public void testBasicMockMultipleReturnValuesEx2() {
        /*
            Internal map for MapStorage is mocked so this will mock getting
            a value from the map.
         */
        String mapKeyValue = "myKey";
        Mockito.when(mapMock.get(mapKeyValue)).thenReturn("myValue1")
                .thenReturn("myValue2")
                .thenReturn("myValue3");

        assertThat(mapStorage.getValue(mapKeyValue)).isEqualTo("myValue1");
        assertThat(mapStorage.getValue(mapKeyValue)).isEqualTo("myValue2");
        assertThat(mapStorage.getValue(mapKeyValue)).isEqualTo("myValue3");

        verify(mapMock, times(3)).get(mapKeyValue);
        verify(mapMock, atMost(3)).get(mapKeyValue);
        verify(mapMock, atLeast(1)).get(mapKeyValue);
        verify(mapMock, never()).size();    // verifies size() was not called on map mock
    }
    /**
     * Mock an object without using annotation
     */
    @Test
    public void testBasicMockNoAnnotation() {
        String expectedValue = "myValue";

        @SuppressWarnings("unchecked")
        Map<String, String> mapMock2 = (Map<String, String>)mock(Map.class);

        when(mapMock.get("myKey")).thenReturn(expectedValue);

        Assertions.assertEquals(mapStorage.getValue("myKey"), expectedValue);
    }

    @Test
    public void testAnyArguments() {
        Mockito.when(mapMock.get(anyString())).thenReturn("value:testAnyArguments()");

        assertThat(mapStorage.getValue("anyKay")).isEqualTo("value:testAnyArguments()");
    }

    /**
     * Mockito.when(<Mock Obj>.<Method call>).thenThrow(<Exception>)
     * Works only with methods that return a value
     * Used in combination with when
     * If testing method that returns void, look at doThrow
     */
    @Test
    public void thenThrowTest() {
        Mockito.when(mapMock.get(anyString())).thenThrow(new NullPointerException("test exception"));

       // System.out.println("Mocked Value: " + mapStorage.getValue("anyKay"));
       Assertions.assertThrows(NullPointerException.class, () -> mapStorage.getValue("anyKey"));
    }

    /**
     * doThrow
     * Usage: void methods or methods that are void but throw an exception
     * Since not return value, mostly testing behavior
     */
    @Test
    public void doThrowTest() {

        // Setup the mock to throw an exception for a void method
        doThrow(new RuntimeException("Exception occurred")).when(mapMock).get(anyString());

        // Now, calling performVoidAction will throw the specified exception
        try {
            mapStorage.getValue("anykey");
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Exception occurred");
        }
    }

    @Test
    public void testVerifyNoInteractions() {
        // Create a mock list
        List<String> mockedList = Mockito.mock(List.class);

        // Do not interact with the mock at all

        // Verify that there were no interactions
        verifyNoInteractions(mockedList);
    }
}
