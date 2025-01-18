package testing.mocking;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TaskRunnerTest {

    @Test
    public void testPerformTaskTimeoutFailure() {
        // Create a mock instance of MyService
        MyService myService = Mockito.mock(MyService.class);

        // Create an instance of TaskRunner with the mocked MyService
        TaskRunner taskRunner = new TaskRunner(myService);

        // Run the task
        taskRunner.run();

        // Verify that performTask() is called within 1 second (1000 milliseconds)
        // This test will fail because performTask() is actually called after 2 seconds
        verify(myService, timeout(2000)).performTask();
    }
}

