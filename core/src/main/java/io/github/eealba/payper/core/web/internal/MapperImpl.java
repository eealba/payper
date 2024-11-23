package io.github.eealba.payper.core.web.internal;

import io.github.eealba.payper.core.Headers;
import io.github.eealba.payper.core.Request;

import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;

public class MapperImpl implements Mapper {
    private static final Mapper INSTANCE = new MapperImpl();
    private MapperImpl() {
    }

    public static Mapper getInstance() {
        return INSTANCE;
    }



    @Override
    public HttpRequest mapRequest(Request request) {
        return null;
    }

    @Override
    public Headers mapHeaders(HttpHeaders headers) {
        return null;
    }
}
