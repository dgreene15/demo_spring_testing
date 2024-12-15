package reactor;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;

@Slf4j
public class ReactorZipTests {

    @Test
    public void testReactorZipBlocking() {
        Mono<String> m1 = Mono.just("m1");
        Mono<String> m2 = Mono.just("m2");

        // not recommend to use .block()
        String result = Mono.zip(m1, m2).map(Tuple2::getT2).block();
        log.info("Result {}", result);
    }

    /*
        Demo zipping two Mono but only using the result from the second Mono.
     */
    @Test
    public void testReactorZip() {

        List<String> items = List.of("item1", "item2", "item3");
        Mono<String> m1 = Mono.just("m1");
        Mono<String> m2 = Mono.just("m2");

        /*
          fromIterable: take Iterable collection, creates Flux and emits elements
          collectList(): from Flux, collects to Mono<List<T>>
          Mono.zip(Mono m1, Mono m2) gives Tuple2 with T1/T2
          flatMap: return Mono, must return value and no void like print ln
         */
        Flux.fromIterable(items)
                .flatMap(item -> Mono.zip(m1, m2).map(Tuple2::getT2)
        ).collectList().flatMap(list -> {
            log.info("Size: {}", list.size());
            log.info("Element: {}", list.get(0));
            return Mono.just(list.size());
        }).subscribe();
    }

    @Test
    public void testReactorZipGetValue() {
        Mono<String> m1 = Mono.just("m1");
        Mono<String> m2 = Mono.just("m2");

        Mono.zip(m1, m2).map(tuple ->
            ItemTuple.builder()
                    .item1(tuple.getT1())
                    .item2(tuple.getT2())
                    .build()
        ).doOnNext(this::processTuple)
                .subscribe();

    }

    private void processTuple(ItemTuple items) {
        System.out.println("Item 1 " + items.item1());
        System.out.println("Item 2 " + items.item2());
    }

    /**
     * toBuilder = true
     *  Give an easy way to copy an copy with same values
     *  can use toBuilder() on object
     */
    @Builder(toBuilder = true)
    private record ItemTuple(
            String item1,
            String item2
    ) {}

    /**
     * zipWhen
     *  Used to combine two or more Mono streams in a coordinated way
     *  Usage: multiple async operations to be concurrent and combine into
     *  single output
     *  Waits for the first mono to complete and then triggers the other Monos.
     *  Combines input and output into a Tuple2
     */
    @Test
    public void testReactorZipWhen() {
        Mono<String> m1 = Mono.just("m1");
        Mono<String> m2 = Mono.just("m2");

        /*
            zipWhen wait for m1 to complete and then
            will process m2
         */
        m1.zipWhen(item1 -> m2).map(tuple ->
                ItemTuple.builder()
                        .item1(tuple.getT1())
                        .item2(tuple.getT2())
                        .build()
        ).doOnNext(this::processTuple)
                .subscribe();

    }

    @Test
    public void testReactorZipWhenExample2() {
        Mono<String> m1 = Mono.just("m1");
        Mono<String> m2 = Mono.just("m2");

        /*
            zipWhen wait for m1 to complete and then
            will process m2
            Example below takes a second parameter to combine the tuples.
         */
        m1.zipWhen(item1 -> m2, Tuples::of).map(tuple ->
                        ItemTuple.builder()
                                .item1(tuple.getT1())
                                .item2(tuple.getT2())
                                .build()
                ).doOnNext(this::processTuple)
                .subscribe();

    }
}
