package testing.mocking;

import com.example.demo.utilities.Utility;
import com.example.demo.service.MyService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class MockitoStaticTests {

    @Test
    public void staticMockTest() {
        try (MockedStatic<Utility> mockedStatic = mockStatic(Utility.class)) {
            mockedStatic.when(() -> Utility.staticMethod("World")) .thenReturn("Mocked Hello, World");

            MyService myService = new MyService();
            String result = myService.greet2("World");

            assertEquals("Mocked Hello, World", result);
        }
    }
}
