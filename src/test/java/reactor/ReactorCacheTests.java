package reactor;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Example to cache values in Reactor stream
 * Puts in the context which is contectual information
 * that travels with the data as it moves through the
 * reactive pipeline.
 */
public class ReactorCacheTests {

    @Test
    public void testReactorCache() {
        ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();
        Mono<Void> cacheValue = Mono.fromRunnable(() -> cache.put("exampleKey", "exampleValue"));
        Mono<Object> retrieveOp = Mono.fromSupplier(() -> cache.get("exampleKey"));

        StepVerifier.create(cacheValue.then(retrieveOp))
                .expectNext("exampleValue")
                .verifyComplete();
    }
}
