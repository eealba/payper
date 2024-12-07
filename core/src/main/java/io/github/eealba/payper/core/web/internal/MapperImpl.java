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
import io.github.eealba.payper.core.web.Request;

import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Edgar Alba
 */
class MapperImpl implements Mapper {
    private static final Mapper INSTANCE = new MapperImpl();

    private MapperImpl() {
    }

    static Mapper getInstance() {
        return INSTANCE;
    }


    @Override
    public HttpRequest mapRequest(Request request) {
        var builder = HttpRequest.newBuilder()
                .uri(request.uri());

        request.timeout().ifPresent(builder::timeout);
        builder.method(request.method().name(), request.bodyPublisher().isPresent() ?
                        mapBodyPublisher(request.bodyPublisher().get()) : HttpRequest.BodyPublishers.noBody());

        var headersArray = request.headers().toArray();
        if (headersArray.length > 0) {
                builder.headers(request.headers().toArray());
        }

        return builder.build();
    }

    private HttpRequest.BodyPublisher mapBodyPublisher(Request.BodyPublisher bodyPublisher) {
        return HttpRequest.BodyPublishers.ofByteArray(bodyPublisher.get());
    }

    @Override
    public Headers mapHeaders(HttpHeaders headers) {
        Map<String, String> headers2 = new HashMap<>();
        headers.map().forEach((key, value) -> headers2.put(key, headers.firstValue(key).orElse("")));
        return new Headers(headers2);
    }
}
