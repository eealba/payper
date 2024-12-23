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

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperProvider;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.PayperResponse;
import io.github.eealba.payper.core.json.Json;

import java.nio.charset.Charset;

/**
 * Implementation of the PayperProvider class.
 * This class provides methods to create Payper objects and request builders.
 *
 * @author Edgar Alba
 * @since 1.0
 */
public class PayperProviderImpl extends PayperProvider {
    private static final Json json = Json.create();

    /**
     * Creates a Payper object.
     *
     * @param config the configuration
     * @return a Payper object
     */
    @Override
    public Payper createPayper(PayperConfig config) {
        return new PayperImpl(config);
    }

    /**
     * Creates a PayperRequest builder.
     *
     * @return a PayperRequest builder
     */
    @Override
    public PayperRequest.Builder createPayperRequestBuilder() {
        return new PayperRequestImpl.RequestBuilder();
    }

    /**
     * Creates a body publisher for the given object.
     *
     * @param obj the object to be published
     * @return a body publisher
     */
    @Override
    public PayperRequest.BodyPublisher bodyPublisherOf(Object obj) {
        return () -> json.toJson(obj).getBytes();
    }

    /**
     * Creates a body handler for the given class.
     *
     * @param <T> the type of the class
     * @param clazz the class
     * @return a body handler
     */
    @Override
    public <T> PayperResponse.BodyHandler<T> bodyHandlerOf(Class<T> clazz) {
        return () -> (Charset cs, byte[] body) -> body == null ? null : json.fromJson(new String(body, cs), clazz);
    }
}