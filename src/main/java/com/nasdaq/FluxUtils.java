package com.nasdaq;

import java.time.Duration;
import java.util.stream.Stream;
import reactor.core.publisher.Flux;

import static reactor.core.publisher.Flux.interval;

public class FluxUtils {

    /**
     * Answer a default instance
     */
    public FluxUtils() {

        super();
    }


    /**
     * Answer a Flux<T> from aStream
     * @param aStream Stream<T>
     * @return Flux<T></T>
     */
    public <T> Flux<T> createFluxFrom(Stream<T> aStream) {

        return Flux.fromStream(aStream);
    }

    /**
     * Create a Flux<Long> for a Duration
     */
    public Flux<Long> createFluxFor(Duration aDuration) {

       return Flux.interval(aDuration);
    }

}
