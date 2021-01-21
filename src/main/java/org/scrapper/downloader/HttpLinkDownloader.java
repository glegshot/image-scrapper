package org.scrapper.downloader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.scrapper.adapter.HttpAdapter;
import org.scrapper.adapter.OkHttpAdapter;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpLinkDownloader implements Downloader {


    HttpAdapter httpAdapter;

    public HttpLinkDownloader() {
        this.httpAdapter = new OkHttpAdapter();
    }

    @Override
    public Map<String, String> download(String destinationPath, List<String> httpLinks) throws IOException {

        Map<String, String> results = new HashMap<>();

        for (String url : httpLinks) {

            InputStream inputStream;
            try (Response response = httpAdapter.execute(url)) {
                if (response.isSuccessful()) {
                    String fileName = extractFileName(url);
                    inputStream = response.body().byteStream();
                    saveToFile(inputStream, destinationPath + "/" + fileName);
                    results.put(url,"OK");
                }
            }catch (Exception e){
                results.put(url, "KO");
                throw e;
            }

        }

        return results;
    }

    private String extractFileName(String url) {
        String[] paths = url.split("/");
        return paths[paths.length - 1];
    }

    private void saveToFile(InputStream inputStream, String file) throws IOException {
        ByteArrayOutputStream outputStream = null;
        FileOutputStream fileOutputStream;

        try {
            outputStream = new ByteArrayOutputStream();
            int currentValue;
            while ((currentValue = inputStream.read()) != -1) {
                outputStream.write(currentValue);
            }

            fileOutputStream = new FileOutputStream(new File(file));
            outputStream.writeTo(fileOutputStream);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

}
