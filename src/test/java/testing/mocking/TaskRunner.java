package testing.mocking;

public class TaskRunner {
    private MyService myService;

    public TaskRunner(MyService myService) {
        this.myService = myService;
    }

    public void run() {
        new Thread(() -> {
            try {
                // Simulate some delay
                Thread.sleep(1000);
                myService.performTask();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
