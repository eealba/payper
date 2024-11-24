package io.github.eealba.payper.core;

import java.net.URI;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

public interface PayperRequest {
    URI uri();
    PayperRequest.Method method();
    Optional<Duration> timeout();
    Optional<PayperRequest.BodyPublisher> bodyPublisher();
    Map<String, String> headers();
    interface Builder {
        PayperRequest.Builder uri(URI uri);
        PayperRequest.Builder header(String name, String value);
        PayperRequest.Builder headers(String... headers);
        PayperRequest.Builder timeout(Duration duration);
        PayperRequest.Builder GET();
        PayperRequest.Builder POST(PayperRequest.BodyPublisher bodyPublisher);
        PayperRequest.Builder PATCH(PayperRequest.BodyPublisher bodyPublisher);
        PayperRequest.Builder PUT(PayperRequest.BodyPublisher bodyPublisher);
        PayperRequest.Builder DELETE();
        PayperRequest build();
    }
    static PayperRequest.Builder newBuilder() {
        return PayperProvider.provider().createPayperRequestBuilder();
    }
    enum Method {
        GET, POST, PUT, DELETE, PATCH
    }
    @FunctionalInterface
    interface BodyPublisher {
        byte[] apply();
    }

    class BodyPublishers {

        public static PayperRequest.BodyPublisher of(Object obj) {
            return PayperProvider.provider().bodyPublisherOf(obj);
        }
        public static PayperRequest.BodyPublisher noBody() {
            return () -> new byte[0];
        }
    }

}
