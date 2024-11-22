package io.github.eealba.payper.core.http.internal;


import io.github.eealba.paypal.core.http.*;
import lombok.extern.java.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The type Http client.
 */
@Log
class HttpClientImpl implements HttpClient {
    private final boolean DEBUG = Boolean
            .parseBoolean(System.getProperty(String.valueOf(HttpClient.class.getName()) + ".DEBUG", "false"));

    private int connectTimeout = 25000;
    private int readTimeout = 25000;

    @Override
    public HttpResponse<String> execute(HttpRequest req) throws IOException {
        return execute(req, new Deserializer<String>() {

            @Override
            public String deserialize(byte[] data) {
                return new String(data, StandardCharsets.UTF_8);
            }

        });
    }

    @Override
    public <T> HttpResponse<T> execute(HttpRequest req, Deserializer<T> deserializer) throws IOException {
        URL url = req.url();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String method = req.method().toString();
        con.setRequestMethod(method);

        for (NameValuePair nvp : req.headers()) {
            con.setRequestProperty(nvp.getName(), nvp.getValue());
        }

        con.setConnectTimeout(this.connectTimeout);
        con.setReadTimeout(this.readTimeout);

        if (this.DEBUG) {

            log.fine("Request " + req.method() + " " + url.toString());
            con.getRequestProperties().forEach((k, v) -> log.fine("Header " + k + ":" + v.get(0)));
        }

        if (HttpMethod.POST == req.method() || HttpMethod.PATCH == req.method()) {
            if (req.body().contentLength() > 0) {

                con.setRequestProperty("Content-Type", req.body().contentType());
                con.setRequestProperty("Content-Length", "" + req.body().contentLength());

                con.setDoOutput(true);
                con.getOutputStream().write(req.body().content());
            } else {
                con.connect();

            }
        } else {
            con.connect();
        }

        int status = con.getResponseCode();
        List<NameValuePair> headers = getResponseHeaders(con);
        byte[] body = getResponseBody(con);
        if (this.DEBUG) {
            log.fine("Response " + status);
            headers.forEach(k -> log.fine("Header " + k.getName() + ":" + k.getValue()));
            log.fine("Body: " + new String(body, StandardCharsets.UTF_8));
        }
        return new HttpResponseImpl<T>(status, headers, body, deserializer);
    }

    private List<NameValuePair> getResponseHeaders(HttpURLConnection con) {
        List<NameValuePair> responseHeaders = new ArrayList<>();
        con.getHeaderFields().entrySet().stream().filter(entry -> (entry.getKey() != null)).forEach(entry -> {
            String name = entry.getKey();

            StringBuilder value = new StringBuilder();
            List<String> headerValues = entry.getValue();
            Iterator<String> it = headerValues.iterator();
            if (it.hasNext()) {
                value.append(it.next());
                while (it.hasNext()) {
                    value.append(", ").append(it.next());
                }
            }
            responseHeaders.add(new BasicNameValuePair(name, value.toString()));
        });
        return responseHeaders;
    }

    private byte[] getResponseBody(HttpURLConnection con) throws IOException {
        BufferedInputStream bis;
        if (con.getResponseCode() > 299) {
            bis = new BufferedInputStream(con.getErrorStream());
        } else {
            bis = new BufferedInputStream(con.getInputStream());
        }

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        int c = 0;
        byte[] tmp = new byte[1024];
        while ((c = bis.read(tmp)) > -1) {
            bao.write(tmp, 0, c);
        }

        bis.close();
        bao.close();
        return bao.toByteArray();
    }

    private void connectTimeout(int milis) {
        this.connectTimeout = milis;
    }

    private void readTimeout(int milis) {
        this.readTimeout = milis;
    }

    /**
     * The type Builder.
     */
    static class Builder implements HttpClientBuilder {
        private int readTimeout;
        private int connectTimeout;

        @Override
        public HttpClient build() {
            HttpClientImpl client = new HttpClientImpl();
            client.readTimeout(this.readTimeout);
            client.connectTimeout(this.connectTimeout);
            return client;
        }

        @Override
        public HttpClientBuilder connectTimeout(int milis) {
            this.connectTimeout = milis;
            return this;
        }

        @Override
        public HttpClientBuilder readTimeout(int milis) {
            this.readTimeout = milis;
            return this;
        }
    }


}
