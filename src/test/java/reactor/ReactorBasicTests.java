package reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * doOn methods
 * doOnNext
 * doOnError
 * doOnComplete
 * doOnSubscribe
 * doOnTerminate
 * doOnCancel
 *  - used for side-effects, they don't alter the stream
 *  - used for logging, debugging, or other side-effects
 */
public class ReactorBasicTests {

    /*
        Example below will consume any error.
     */
    @Test
    public void testDoOnSuccessError() {

        Mono.just("stream data")
                .map(String::toUpperCase)
                .doOnSuccess(System.out::println)
                .doOnError(System.out::println)
                .onErrorReturn("updated error data")
                .subscribe(
                        value -> System.out.println("Value: " + value),
                        error -> System.out.println("Error: " + error)
                );
    }

    /*
        Demo setting static value outside of stream.
        Demo setting object property outside of stream.
     */
    static int i = 0;
    @Test
    public void testSubscribe() {
        Mono.delay(Duration.ofMillis(1)).map(d -> {
            System.out.println("Value: " + d);
            return d;
        }).subscribe(System.out::println);

        MyElement myElement = new MyElement();

        /*
        doOnNext: side-affect for processing and continue passing elements down stream
        Variable used in lambda expression should be final or effectively final
            - works with static value
            - work if use a POJO since a reference doesn't change
         */
        Flux.just(1, 2, 3, 4).doOnNext(
                element -> {
                    i = 5;
                    myElement.setName("hi");
                    System.out.println("Hi: " + element);
                }
        ).flatMap(Flux::just).subscribe(System.out::println);

        System.out.println("Value of i: " + i);
        System.out.println("MyElement: " + myElement.getName());
    }

    static class MyElement {
        String firstName;
        public void setName(String name) {
            firstName = name;
        }

        public String getName() {
            return firstName;
        }
    }
}
