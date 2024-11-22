package io.github.eealba.payper.core.http.internal;

import io.github.eealba.paypal.core.http.*;
import lombok.extern.java.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

/**
 * The type Http request.
 */
@Log
class HttpRequestImpl implements HttpRequest {
    private final List<NameValuePair> headers;
    private final List<NameValuePair> queryParameters;
    private final List<NameValuePair> pathParameters;
    private final URL url;
    private final HttpBody body;
    private final HttpMethod method;

    private HttpRequestImpl(Builder builder) {
        url = Objects.requireNonNull(builder.url);
        method = Objects.requireNonNull(builder.method);
        body = builder.body;

        List<NameValuePair> _headers = new ArrayList<>();
        List<NameValuePair> _queries = new ArrayList<>();
        List<NameValuePair> _pathParameters = new ArrayList<>();

        builder.headers.forEach(k -> _headers.add(k));
        builder.queryParameters.forEach(k -> _queries.add(k));
        builder.pathParameters.forEach(k -> _pathParameters.add(k));

        this.headers = Collections.unmodifiableList(_headers);
        this.queryParameters = Collections.unmodifiableList(_queries);
        this.pathParameters = Collections.unmodifiableList(_pathParameters);

    }

    @Override
    public List<NameValuePair> headers() {
        return this.headers;
    }

    @Override
    public HttpBody body() {
        return this.body;
    }

    @Override
    public HttpMethod method() {
        return this.method;
    }

    @Override
    public URL url() {
        StringBuilder url = new StringBuilder(this.url.toExternalForm());
        replacePathParameters(url);
        concatQueryParameters(url);
        try {
            return new URL(url.toString());
        } catch (MalformedURLException e) {
            log.log(Level.SEVERE, "Bad url", e);
        }
        return null;
    }

    private void replacePathParameters(StringBuilder buff) {
        if (this.pathParameters.isEmpty()) {
            return;
        }
        this.pathParameters.forEach(nv -> {
            String vari = "{" + nv.getName() + "}";
            int pos = buff.indexOf(vari);
            while (pos > -1) {
                buff.replace(pos, pos + vari.length(), nv.getValue());
                pos = buff.indexOf(vari);
            }
        });

    }

    private void concatQueryParameters(StringBuilder buff) {
        if (this.queryParameters.isEmpty()) {
            return;
        }
        buff.append("?");
        for (NameValuePair nvp : this.queryParameters) {
            buff.append(nvp.getName());
            buff.append("=");
            buff.append(nvp.getValue());
            buff.append("&");
        }
        buff.setLength(buff.length() - 1);

    }

    /**
     * The type Builder.
     */
    static class Builder implements HttpRequestBuilder {
        private final List<NameValuePair> headers = new ArrayList<>();
        private final List<NameValuePair> queryParameters = new ArrayList<>();
        private final List<NameValuePair> pathParameters = new ArrayList<>();
        private final URL url;
        private final HttpMethod method;
        private HttpBody body;

        /**
         * Instantiates a new Builder.
         *
         * @param method the method
         * @param url    the url
         */
        Builder(HttpMethod method, URL url) {
            this.method = method;
            this.url = url;

        }

        @Override
        public HttpRequest build() {
            if (body == null) {
                body = Http.newHttpBodyFactory().createEmptyBody();
            }

            HttpRequestImpl request = new HttpRequestImpl(this);
            return request;
        }

        @Override
        public HttpRequestBuilder header(String name, String value) {
            this.headers.add(new BasicNameValuePair(name, value));
            return this;
        }

        @Override
        public HttpRequestBuilder queryParameter(String name, String value) {
            this.queryParameters.add(new BasicNameValuePair(name, value));
            return this;
        }

        @Override
        public HttpRequestBuilder pathParameter(String name, String value) {
            this.pathParameters.add(new BasicNameValuePair(name, value));
            return this;
        }

        @Override
        public HttpRequestBuilder body(HttpBody body) {
            this.body = body;
            return this;
        }

    }

}
