package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.Headers;
import io.github.eealba.payper.core.Response;

import java.net.http.HttpResponse;


class EmptyResponseImpl<Void> implements Response<Void> {


    private final HttpResponse<Void> httpResponse;
    private final Mapper mapper = MapperImpl.getInstance();

    public EmptyResponseImpl(HttpResponse<Void> httpResponse) {
        this.httpResponse = httpResponse;
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
    public Void body() {
        return httpResponse.body();
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
