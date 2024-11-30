/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.eealba.payper.core.web.internal;

import io.github.eealba.payper.core.web.Headers;
import io.github.eealba.payper.core.web.Method;
import io.github.eealba.payper.core.web.Request;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Author: Edgar Alba
 */
class RequestImpl implements Request {
    private final URI uri;
    private final Method method;
    private final Duration timeout;
    private final Headers headers;
    private final BodyPublisher bodyPublisher;

    private RequestImpl(RequestBuilder requestBuilder) {
        this.uri = requestBuilder.uri;
        this.method = requestBuilder.method;
        this.timeout = requestBuilder.timeout;
        this.headers = new Headers(requestBuilder.headers);
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
    public Headers headers() {
        return headers;
    }
    static class RequestBuilder implements Request.Builder {
        private URI uri;
        private final Map<String, String> headers = new HashMap<>();
        private Duration timeout;
        private Method method;
        private Request.BodyPublisher bodyPublisher;

        @Override
        public Request.Builder uri(URI uri) {
            this.uri = uri;
            return this;
        }

        @Override
        public Request.Builder header(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        @Override
        public Request.Builder headers(String... headers) {
            for (int i = 0; i < headers.length; i += 2) {
                this.headers.put(headers[i],headers[i + 1]);
            }
            return this;
        }

        @Override
        public Request.Builder timeout(Duration duration) {
            this.timeout = duration;
            return this;
        }

        @Override
        public Request.Builder GET() {
            this.method = Method.GET;
            this.bodyPublisher = Request.BodyPublishers.noBody();
            return this;
        }

        @Override
        public Request.Builder POST(Request.BodyPublisher bodyPublisher) {
            this.method = Method.POST;
            this.bodyPublisher = bodyPublisher;
            return this;
        }

        @Override
        public Request.Builder PATCH(Request.BodyPublisher bodyPublisher) {
            this.method = Method.PATCH;
            this.bodyPublisher = bodyPublisher;
            return this;
        }

        @Override
        public Request.Builder PUT(Request.BodyPublisher bodyPublisher) {
            this.method = Method.PUT;
            this.bodyPublisher = bodyPublisher;
            return this;
        }

        @Override
        public Request.Builder DELETE() {
            this.method = Method.DELETE;
            this.bodyPublisher = Request.BodyPublishers.noBody();
            return this;
        }

        @Override
        public Request build() {
            return new RequestImpl(this);
        }
    }
}
