package io.github.eealba.payper.core.web.internal;
import io.github.eealba.payper.core.web.Headers;
import io.github.eealba.payper.core.web.Response;

import java.net.http.HttpResponse;
import java.util.function.Function;


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
