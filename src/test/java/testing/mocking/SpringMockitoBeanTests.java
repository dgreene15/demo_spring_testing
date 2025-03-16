package testing.mocking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @MockitoBean
 * - part of the sprint framework
 * - creates mock objects and integrates them into the Spring application context
 * - used for integration tests with Spring
 * - unlike @Mock which is used for unit tests
 *
 * @MockBean - deprecated with SB 3.4.0 to use @MockitoBean
 */
@SpringBootTest
@ContextConfiguration(classes = SpringMockitoBeanTests.MyConfig.class)
public class SpringMockitoBeanTests {

    @Test
    void testMockBean() {
        when(myRepository.fetchData()).thenReturn("Mocked Data");
        String result = myService.getData();

        assertEquals("Mocked Data", result);
    }

    static class SpringMockBean_MyService {
        private final SpringMockBean_MyRepository myRepository;

        @Autowired
        public SpringMockBean_MyService(SpringMockBean_MyRepository myRepository) {
            this.myRepository = myRepository;
        }

        public String getData() {
            return myRepository.fetchData();
        }
    }

    static class SpringMockBean_MyRepository {
        public String fetchData() {
            return "Real Data";
        }
    }

    @Configuration
    static class MyConfig {
        @Bean
        public SpringMockBean_MyService myService(SpringMockBean_MyRepository myRepository) {
            return new SpringMockBean_MyService(myRepository);
        }
    }

    @Autowired
    private SpringMockBean_MyService myService;

    @MockitoBean
    private SpringMockBean_MyRepository myRepository;
}
