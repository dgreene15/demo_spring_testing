package testing.mocking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
public class MockitoInOrderTests {
    @Mock
    private ServiceA serviceAMock;

    @Mock
    private ServiceB serviceBMock;

    @InjectMocks
    private MyInOrderService myService;

    @Test
    public void testProcessInOrder() {
        // Call the method to be tested
        myService.process();

        // Verify the order of interactions
        InOrder inOrder = inOrder(serviceAMock, serviceBMock);
        inOrder.verify(serviceAMock).performAction();
        inOrder.verify(serviceBMock).performAction();
    }
}

class ServiceA {
    public void performAction() {
        // implementation
    }
}

class ServiceB {
    public void performAction() {
        // implementation
    }
}

class MyInOrderService {
    private final ServiceA serviceA;
    private final ServiceB serviceB;

    public MyInOrderService(ServiceA serviceA, ServiceB serviceB) {
        this.serviceA = serviceA;
        this.serviceB = serviceB;
    }

    public void process() {
        serviceA.performAction();
        serviceB.performAction();
    }
}

