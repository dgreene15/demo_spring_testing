package testing.mocking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.answer;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.service.MyService;
import org.mockito.stubbing.Answer;

public class MockitoAnswerTests {
    //@Mock
    MyService myService = mock(MyService.class);

    @Test
    public void answerTests() {
        when(myService.compute(anyInt())).thenAnswer(answer(MockitoAnswerTests::buildComputeAnswer));

        Assertions.assertEquals(1, myService.compute(2));
    }

    @Test
    public void returnArgTest() {
        when(myService.compute(anyInt())).then(AdditionalAnswers.returnsFirstArg());

        Assertions.assertEquals(myService.compute(5), 5);
    }

    private static int buildComputeAnswer(int input) {
        return 1;
    }
}
