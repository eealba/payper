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
package io.github.eealba.payper.core.web;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;

public interface Request {
    URI uri();
    Method method();
    Optional<Duration> timeout();
    Optional<Request.BodyPublisher> bodyPublisher();
    Headers headers();
    interface Builder {
        Request.Builder uri(URI uri);
        Request.Builder header(String name, String value);
        Request.Builder headers(String... headers);
        Request.Builder timeout(Duration duration);
        Request.Builder GET();
        Request.Builder POST(Request.BodyPublisher bodyPublisher);
        Request.Builder PATCH(Request.BodyPublisher bodyPublisher);
        Request.Builder PUT(Request.BodyPublisher bodyPublisher);
        Request.Builder DELETE();
        Request build();
    }
    static Request.Builder newBuilder() {
        return WebProvider.provider().createRequestBuilder();
    }
    @FunctionalInterface
    interface BodyPublisher {
        byte[] get();
    }

    class BodyPublishers {
        public static Request.BodyPublisher ofString(String str) {
            return () -> str.getBytes(StandardCharsets.UTF_8);
        }
        public static Request.BodyPublisher ofString(String s, Charset charset) {
            return () -> s.getBytes(charset);
        }
        public static Request.BodyPublisher ofByteArray(byte[] buf) {
            return () -> buf;
        }

        public static BodyPublisher noBody() {
            return () -> null;
        }
    }
}
