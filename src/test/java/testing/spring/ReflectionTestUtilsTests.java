package testing.spring;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;


public class ReflectionTestUtilsTests {
    @Test
    public void testPrivateFieldAndMethod() {
        // Create an instance of the class
        Person person = new Person();

        // Set private field 'age'
        org.springframework.test.util.ReflectionTestUtils.setField(person, "age", 30);

        // Get private field 'age'
        int age = (Integer) ReflectionTestUtils.getField(person, "age");
        assertEquals(30, age);

        // Invoke private method 'getSecretName'
        String secretName = (String) ReflectionTestUtils.invokeMethod(person, "getPrivateDefinedName");
        assertEquals("Field  30", secretName);
    }
}

class Person {
    private int age;

    private String getPrivateDefinedName() {
        return "Field  " + age;
    }
}
