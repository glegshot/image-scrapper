package org.scrapper.downloader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpLinkDownloader implements Downloader {

    OkHttpClient httpClient;

    public HttpLinkDownloader(){
        this.httpClient = new OkHttpClient();
    }

    @Override
    public Map<String, String> download(File destinationPath, List<String> httpLinks) throws IOException {

        Request request;
        for(String url : httpLinks){

            request = new Request.Builder().url(url).build();

            try(Response response = httpClient.newCall(request).execute()){
                if(response.isSuccessful()){
                    InputStream inputStream = response.body().byteStream();

                }
            }


        }


        Map<String, String> results = new HashMap<>();
        results.put("https://games.mxdwn.com/wp-content/uploads/2020/11/doooom.jpg", "OK");
        results.put("https://specials-images.forbesimg.com/imageserve/5dc5a8f1ca425400073c556a/960x0.jpg", "OK");
        return results;
    }
}
