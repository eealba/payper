package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.BodyHandler;
import io.github.eealba.payper.core.Headers;
import io.github.eealba.payper.core.Response;

import java.io.InputStream;
import java.net.http.HttpResponse;


public class ResponseImpl<T> implements Response<T> {


    private final HttpResponse<InputStream> httpResponse;
    private final BodyHandler<T> bodyHandler;
    private final Mapper mapper = MapperImpl.getInstance();

    public ResponseImpl(HttpResponse<InputStream> httpResponse, BodyHandler<T> bodyHandler) {
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
