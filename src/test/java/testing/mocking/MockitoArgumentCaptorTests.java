package testing.mocking;

import com.example.demo.utilities.MapStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.verify;

/**
 * ArgumentCaptor
 * - Used to capture an argument passed to a method to inspect it
 */
@ExtendWith(MockitoExtension.class)
public class MockitoArgumentCaptorTests {

    @Mock
    Map<String, String> mapMock;

    @InjectMocks
    MapStorage mapStorage = new MapStorage();

    @Captor
    ArgumentCaptor<String> keyCaptor;

    @Captor
    ArgumentCaptor<String> valueCaptor;

    @Test
    public void testArgumentCaptor() {

        mapStorage.add("hi", "hello");
        verify(mapMock).put(keyCaptor.capture(), valueCaptor.capture());

        System.out.println("KeyCaptor: " + keyCaptor.getValue());
        System.out.println("ValueCaptor: " + valueCaptor.getValue());
    }
}
