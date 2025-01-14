package testing.mocking;

import com.example.demo.utilities.MapStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.*;

/**
 * BDDMockito
 * - Same as Mockito but different method names
 *
 * given() verse when()
 * wilReturn() verse thenReturn()
 * then().should() verse verify()
 * given().willThrow verse when().thenThrow()
 * assertThrows verse assertThatExceptionOfType().isThrownBy()
 */
@ExtendWith(MockitoExtension.class)
public class BDDMockitoTests {

    @Mock
    MyMock myMock;

    @Mock
    Map<String, String> mapMock;

    @InjectMocks
    MapStorage mapStorage = new MapStorage();

    @Test
    public void basicsTests() {
        given(mapMock.get("myKey")).willReturn("myValue");

        assertThat(mapStorage.getValue("myKey")).isEqualTo("myValue");

        then(mapMock).should().get("myKey");
        then(mapMock).should().get(anyString());
        then(mapMock).should(atMost(1)).get("myKey");
        then(mapMock).should(never()).size();
    }

    @Test
    public void willThrowTests() {
        IllegalArgumentException cause = new IllegalArgumentException("Input cannot be null");
        given(myMock.performAction("1")).willThrow(new IllegalArgumentException("Input cannot be null", cause));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> myMock.performAction("1"))
                .withCause(cause)
                .withMessage("Input cannot be null");

    }


}

class MyMock {
    public String performAction(String input) {
        if (input.equals("1")) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        return "2";
    }
}
