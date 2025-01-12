package podam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PodamTests {

    /**
     * manufacturePojo is used to create a new object with random data
     * doesn't fill all field, especially those requiring complex initialization
     */
    @Test
    public void manufacturePojo() {
        PodamFactory factory = new PodamFactoryImpl();
        PodamTestPojo myPojo = factory.manufacturePojo(PodamTestPojo.class);

        assertThat(myPojo.id).isGreaterThan(0);
        assertThat(myPojo.description).isNotEmpty();
        assertThat(myPojo.items).isNotEmpty();
    }

    /**
     * manufacturePojoWithFullData is used to create a new object with all fields populated
     * use most complex constructor and fill in even complex initialization fields
     */
    @Test
    public void manufacturePojoWithFullData() {
        PodamFactory factory = new PodamFactoryImpl();
        PodamTestPojo myPojo = factory.manufacturePojoWithFullData(PodamTestPojo.class);

        assertThat(myPojo.id).isGreaterThan(0);
        assertThat(myPojo.description).isNotEmpty();
        assertThat(myPojo.items).isNotEmpty();
    }

    /**
     * populatePojo is used to populate an existing object with random data
     */
    @Test
    public void populatePojo() {
        PodamFactory factory = new PodamFactoryImpl();
        PodamTestPojo defaultPojo = new PodamTestPojo(1, "test", Arrays.asList("hi", "hi2"));
        PodamTestPojo actual = factory.populatePojo(defaultPojo);

        assertThat(actual.id).isGreaterThan(0);
        assertThat(actual.description).isNotEmpty();
        assertThat(actual.items).isNotEmpty();
    }
}


