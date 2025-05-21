package testing.mocking;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MockitoTests {

    @Test
    void verifyTest() {
        List<String> mockList = mock(List.class);

        mockList.add("Hello");
        mockList.add("World");

        verify(mockList).add("Hello");
        verify(mockList).add("World");

        verify(mockList, times(2)).add(anyString());

        verify(mockList, never()).clear();
    }
}
