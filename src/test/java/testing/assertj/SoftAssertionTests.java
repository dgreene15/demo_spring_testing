package testing.assertj;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SoftAssertionTests {
    @Test
    public void testSoftAssertions() {
        SoftAssertions softly = new SoftAssertions();
        String name = "John Doe";
        int age = 30;
        String city = "New York";

        softly.assertThat(name)
                .startsWith("J")
                .endsWith("e")
                .isEqualToIgnoringCase("john doe");

        softly.assertThat(age)
                .isGreaterThan(25)
                .isLessThan(35)
                .isEqualTo(31); // This will fail

        softly.assertThat(city)
                .isEqualTo("New York")
                .contains("York");

        try {
            softly.assertAll();
        } catch (AssertionError e) {
            assertThat(e.getMessage())
                    .contains("expected: 31");
        }

    }
}
