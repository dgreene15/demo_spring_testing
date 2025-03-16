package reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.PATH;

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
    public void doOnSuccess() {

        Mono<String> actual =
                Mono.just("stream data")
                .map(String::toUpperCase)
                .doOnSuccess(value ->
                        assertThat(value).isEqualTo("STREAM DATA"));

        StepVerifier.create(actual)
                .expectNext("STREAM DATA")
                .verifyComplete();
    }

    @Test
    public void delayElement() {
        Mono<String> actual = Mono.just("delayed string")
                .delayElement(Duration.ofSeconds(1))
                .map(String::toUpperCase);

        StepVerifier.create(actual)
                .expectNext("DELAYED STRING")
                .verifyComplete();
    }

    /*
        Demo setting static value outside of stream.
        Demo setting object property outside of stream.
     */
    static int i = 0;
    @Test
    public void testSubscribe() {
        MyElement myElement = new MyElement();
        /*
        doOnNext: side-affect for processing and continue passing elements down stream
        Variable used in lambda expression should be final or effectively final
            - works with static value
            - work if use a POJO since a reference doesn't change
         */
        Flux<Integer> actual = Flux.just(1, 2, 3, 4).doOnNext(
                element -> {
                    i = 5;
                    myElement.setName("hi");
                }
        ).flatMap(Flux::just);

        StepVerifier.create(actual)
                .expectNext(1, 2, 3, 4)
                .verifyComplete();

        assertThat(i).isEqualTo(5);
        assertThat(myElement.getName()).isEqualTo("hi");
    }

    @Test
    public void testSwitchOnEmpty() {
        Flux<Integer> actual = Flux.<Integer>empty()
                .switchIfEmpty(Flux.just(1, 2, 3, 4));

        StepVerifier.create(actual)
                .expectNext(1, 2, 3, 4)
                .verifyComplete();
    }

    /**
     * Mono.defer
     * Mono that only generates its value (or error) when it is subscribed to.
     */
    @Test
    public void testMonoDefer() {
        Mono<Integer> actual = Mono.defer(() -> Mono.just(1));

        StepVerifier.create(actual)
                .expectNext(1)
                .verifyComplete();
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
