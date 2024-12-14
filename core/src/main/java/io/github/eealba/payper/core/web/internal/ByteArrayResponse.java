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
import io.github.eealba.payper.core.web.Response;

import java.net.http.HttpResponse;
import java.util.function.Function;


/**
 * Author: Edgar Alba
 */
class ByteArrayResponse<T> implements Response<T> {


    private final HttpResponse<byte[]> httpResponse;
    private final BodyHandler<T> bodyHandler;
    private final Mapper mapper = MapperImpl.getInstance();

    public ByteArrayResponse(HttpResponse<byte[]> httpResponse, BodyHandler<T> bodyHandler) {
        this.httpResponse = httpResponse;
        this.bodyHandler = bodyHandler;
    }

    /**
     * Status code int.
     *
     * @return the int
     */
    @Override
    public int statusCode() {
        return httpResponse.statusCode();
    }

    /**
     * Body t.
     *
     * @return the t
     */
    @Override
    public T body() {
        Function<byte[], T> bodyHandler = this.bodyHandler.apply(this);
        return bodyHandler.apply(httpResponse.body());
    }

    /**
     * Headers headers.
     *
     * @return the headers
     */
    @Override
    public Headers headers() {
        return mapper.mapHeaders(httpResponse.headers());
    }
}
