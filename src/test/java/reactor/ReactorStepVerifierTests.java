package reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactorStepVerifierTests {

    @Test
    public void testStepVerifier() {
        Flux<String> source = Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
                .filter(name -> name.length() == 4)
                .map(String::toUpperCase);
        StepVerifier
                .create(source)
                .expectNext("JOHN")
                .expectNextMatches(name -> name.startsWith("MA"))
                .expectNext("CLOE", "CATE")
                .expectComplete()
                .verify();
    }

    @Test
    public void testStepVerifierVerifyComplete() {
        Flux<String> source = Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
                .filter(name -> name.length() == 4)
                .map(String::toUpperCase);
        StepVerifier
                .create(source)
                .expectNext("JOHN")
                .expectNextMatches(name -> name.startsWith("MA"))
                .expectNext("CLOE", "CATE")
                .verifyComplete();
    }

    @Test
    public void testStepVerifierError() {
        Flux<String> source = Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
                .filter(name -> name.length() == 4)
                .map(String::toUpperCase);

        Flux<String> error = source.concatWith(Mono.error(new IllegalArgumentException("Our message")));

        StepVerifier
                .create(error)
                .expectNextCount(4)
                .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException &&
                        throwable.getMessage().equals("Our message"))
                .verify();
    }

    @Test
    public void testMonoWithError() {

        Mono<String> monoWithError = Mono.error(new RuntimeException("Something went wrong"));

        StepVerifier.create(monoWithError)
                .expectErrorSatisfies(error -> {
                    // Verify that the error is an instance of RuntimeException
                    assertThat(error).isInstanceOf(RuntimeException.class);
                    // Verify the error message
                    assertThat(error.getMessage()).isEqualTo("Something went wrong");
                })
                .verify();
    }
}
