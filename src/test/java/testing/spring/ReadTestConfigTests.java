package testing.spring;

import com.example.demo.DemoApplication;
import com.example.demo.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DemoApplication.class)
@Import(MyTestConfiguration.class)
public class ReadTestConfigTests {

    @Autowired
    private MyService myService;

    @Test
    public void testGreet() {
        String greeting = myService.greet("test");
        assertThat(greeting).isEqualTo("(inside MyTestConfiguration class) Hello, test");
    }
}
