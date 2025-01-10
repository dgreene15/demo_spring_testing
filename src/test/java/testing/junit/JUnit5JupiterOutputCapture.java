package testing.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
public class JUnit5JupiterOutputCapture {
    private final OutputService myService = new OutputService();

    @Test
    public void testPrintMessage(CapturedOutput output) {
        // Call the method that prints to the console
        myService.printMessage("Hello, world!");

        // Assert that the output was captured correctly
        assertThat(output).contains("Hello, world!");
    }
}

class OutputService {
    public void printMessage(String message) {
        System.out.println(message);
    }
}
