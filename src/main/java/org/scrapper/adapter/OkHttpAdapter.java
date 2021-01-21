package org.scrapper.adapter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpAdapter implements HttpAdapter {

    OkHttpClient httpClient;

    public OkHttpAdapter() {
        this.httpClient = new OkHttpClient();
    }

    @Override
    public Response execute(String URL) throws IOException {
        Request request = new Request.Builder().url(URL).build();
        Response response = httpClient.newCall(request).execute();
        return response;
    }
}
