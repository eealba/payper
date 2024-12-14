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

package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.web.Request;
import io.github.eealba.payper.core.web.WebClientConfig;

import java.net.URI;
import java.time.Duration;

/**
 * The Mapper class is responsible for mapping configurations and requests.
 *
 * @author Edgar Alba
 * @since 1.0
 */
class Mapper {

    /**
     * Maps the PayperConfig to WebClientConfig.
     *
     * @param config the Payper configuration
     * @return the WebClient configuration
     */
    static WebClientConfig mapWebClientConfig(PayperConfig config) {
        var builder = WebClientConfig.builder();
        builder.connectTimeout(config.connectTimeout().orElse(Duration.ofSeconds(30)));
        builder.executor(config.executor().orElse(null));
        return builder.build();
    }

    /**
     * Maps the PayperRequest to a Request with the given token.
     *
     * @param config the Payper configuration
     * @param request the Payper request
     * @param token the token
     * @return the mapped Request
     */
    static Request mapRequest(PayperConfig config, PayperRequest request, TokenImpl token) {
        var builder = Request.newBuilder();
        builder.uri(URI.create(config.authenticator().getBaseUrl() + request.path()));
        request.headers().forEach(builder::header);
        builder.authorization(Request.Authorizations.BEARER(token.accessToken()));
        request.timeout().ifPresent(builder::timeout);

        switch (request.method()) {
            case GET -> builder.GET();
            case POST -> builder.POST(mapPublisher(request.bodyPublisher().orElse(null)));
            case PUT -> builder.PUT(mapPublisher(request.bodyPublisher().orElse(null)));
            case DELETE -> builder.DELETE();
            case PATCH -> builder.PATCH(mapPublisher(request.bodyPublisher().orElse(null)));
        }
        return builder.build();
    }

    /**
     * Maps the PayperRequest.BodyPublisher to a Request.BodyPublisher.
     *
     * @param bodyPublisher the Payper request body publisher
     * @return the mapped Request body publisher
     */
    private static Request.BodyPublisher mapPublisher(PayperRequest.BodyPublisher bodyPublisher) {
        if (bodyPublisher == null) {
            return Request.BodyPublishers.noBody();
        }
        return bodyPublisher::apply;
    }
}