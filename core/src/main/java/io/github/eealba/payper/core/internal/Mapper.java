package io.github.eealba.payper.core.internal;

import io.github.eealba.payper.core.Headers;
import io.github.eealba.payper.core.Request;

import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;

public interface Mapper {

    HttpRequest mapRequest(Request request);

    Headers mapHeaders(HttpHeaders headers);
}
