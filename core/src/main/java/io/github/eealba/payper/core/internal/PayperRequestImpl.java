package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.PayperRequest;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class PayperRequestImpl implements PayperRequest {
    private final URI uri;
    private final Method method;
    private final Duration timeout;
    private final Map<String, String> headers;
    private final BodyPublisher bodyPublisher;

    private PayperRequestImpl(RequestBuilder requestBuilder) {
        this.uri = requestBuilder.uri;
        this.method = requestBuilder.method;
        this.timeout = requestBuilder.timeout;
        this.headers = requestBuilder.headers;
        this.bodyPublisher = requestBuilder.bodyPublisher;
    }

    @Override
    public URI uri() {
        return uri;
    }

    @Override
    public Method method() {
        return method;
    }

    @Override
    public Optional<Duration> timeout() {
        return Optional.ofNullable(timeout);
    }

    @Override
    public Optional<BodyPublisher> bodyPublisher() {
        return Optional.ofNullable(bodyPublisher);
    }

    @Override
    public Map<String, String> headers() {
        return headers;
    }
    static class RequestBuilder implements Builder {
        private URI uri;
        private final Map<String, String> headers = new HashMap<>();
        private Duration timeout;
        private Method method;
        private BodyPublisher bodyPublisher;

        @Override
        public Builder uri(URI uri) {
            this.uri = uri;
            return this;
        }

        @Override
        public Builder header(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        @Override
        public Builder headers(String... headers) {
            for (int i = 0; i < headers.length; i += 2) {
                this.headers.put(headers[i],headers[i + 1]);
            }
            return this;
        }

        @Override
        public Builder timeout(Duration duration) {
            this.timeout = duration;
            return this;
        }

        @Override
        public Builder GET() {
            this.method = Method.GET;
            this.bodyPublisher = BodyPublishers.noBody();
            return this;
        }

        @Override
        public Builder POST(BodyPublisher bodyPublisher) {
            this.method = Method.POST;
            this.bodyPublisher = bodyPublisher;
            return this;
        }

        @Override
        public Builder PATCH(BodyPublisher bodyPublisher) {
            this.method = Method.PATCH;
            this.bodyPublisher = bodyPublisher;
            return this;
        }

        @Override
        public Builder PUT(BodyPublisher bodyPublisher) {
            this.method = Method.PUT;
            this.bodyPublisher = bodyPublisher;
            return this;
        }

        @Override
        public Builder DELETE() {
            this.method = Method.DELETE;
            this.bodyPublisher = BodyPublishers.noBody();
            return this;
        }

        @Override
        public PayperRequest build() {
            return new PayperRequestImpl(this);
        }
    }
}
