package io.github.eealba.payper.core.web.internal;

import io.github.eealba.payper.core.web.Headers;
import io.github.eealba.payper.core.web.Request;

import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;

public interface Mapper {

    HttpRequest mapRequest(Request request);

    Headers mapHeaders(HttpHeaders headers);
}
