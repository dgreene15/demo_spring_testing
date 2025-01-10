package podam;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Arrays;

@Slf4j
public class PodamTests {

    @Test
    public void podamTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TestPojo myPojo = factory.manufacturePojo(TestPojo.class);

        log.info("id: {}; description: {}", myPojo.id, myPojo.description);

        TestPojo myPojo2 = factory.manufacturePojoWithFullData(TestPojo.class);
        log.info("id: {}; description: {}", myPojo2.id, myPojo2.description);

        TestPojo myPojo3 = new TestPojo(1, "test", Arrays.asList("hi", "hi2"));
        factory.populatePojo(myPojo3);
        log.info("id: {}; description: {}", myPojo3.id, myPojo3.description);
    }
}

