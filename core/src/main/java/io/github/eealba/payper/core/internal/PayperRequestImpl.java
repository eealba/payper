package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.PayperRequest;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class PayperRequestImpl implements PayperRequest {
    private final String path;
    private final Method method;
    private final Duration timeout;
    private final Map<String, String> headers;
    private final BodyPublisher bodyPublisher;

    private PayperRequestImpl(RequestBuilder requestBuilder) {
        this.path = requestBuilder.resolvePath();
        this.method = requestBuilder.method;
        this.timeout = requestBuilder.timeout;
        this.headers = requestBuilder.headers;
        this.bodyPublisher = requestBuilder.bodyPublisher;
    }

    @Override
    public String path() {
        return path;
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
        private String path;
        private final Map<String, String> headers = new HashMap<>();
        private final Map<String, String> queryParams = new HashMap<>();
        private final Map<String, String> pathParams = new HashMap<>();
        private Duration timeout;
        private Method method;
        private BodyPublisher bodyPublisher;

        @Override
        public Builder path(String path) {
            this.path = path;
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
        public Builder query(String name, String value) {
            this.queryParams.put(name, value);
            return this;
        }

        @Override
        public Builder queries(String... queryParams) {
            for (int i = 0; i < queryParams.length; i += 2) {
                this.queryParams.put(queryParams[i],queryParams[i + 1]);
            }
            return this;
        }

        @Override
        public Builder pathParameter(String name, String value) {
            this.pathParams.put(name, value);
            return this;
        }

        @Override
        public Builder pathParameters(String... pathParams) {
            for (int i = 0; i < pathParams.length; i += 2) {
                this.pathParams.put(pathParams[i], pathParams[i + 1]);
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

        private String resolvePath() {
            var path = this.path;
            for (var entry : pathParams.entrySet()) {
                path = path.replace("{" + entry.getKey() + "}", entry.getValue());
            }
            if (!queryParams.isEmpty()) {
                var builder = new StringBuilder(path);
                builder.append("?");
                for (var entry : queryParams.entrySet()) {
                    builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
                path = builder.substring(0, builder.length() - 1);
            }
            return path;
        }
    }
}
