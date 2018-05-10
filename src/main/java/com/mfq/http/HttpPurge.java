package com.mfq.http;

import java.net.URI;
import org.apache.http.client.methods.HttpRequestBase;

public class HttpPurge extends HttpRequestBase {
    public static final String METHOD_NAME = "PURGE";

    public HttpPurge() {
    }

    public String getMethod() {
        return "PURGE";
    }

    public HttpPurge(String uri) {
        this.setURI(URI.create(uri));
    }

    public String getName() {
        return "PURGE";
    }
}

