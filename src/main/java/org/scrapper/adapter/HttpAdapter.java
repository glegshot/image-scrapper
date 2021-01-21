package org.scrapper.adapter;

import okhttp3.Response;

import java.io.IOException;

public interface HttpAdapter {

    Response execute(String URL) throws IOException;

}
