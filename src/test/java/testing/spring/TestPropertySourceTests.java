package testing.spring;

import com.example.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource("classpath:test.properties")
public class TestPropertySourceTests {
    @Value("${dwg.test}")
    private String testProperty;

    @Test
    public void testProperties() {
        assertEquals("hello", testProperty);
    }

}
