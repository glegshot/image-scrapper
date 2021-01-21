package org.scrapper.downloader;

import okhttp3.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.scrapper.adapter.HttpAdapter;
import org.scrapper.adapter.OkHttpAdapter;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(JUnit4.class)
public class HttpLinkDownloaderTest {

    Downloader httpLinkDownloader;

    HttpAdapter httpAdapter;

    Response successResponse;
    Response failResponse;

    @Before
    public void initMocks() {
        httpAdapter = Mockito.mock(OkHttpAdapter.class);
        httpLinkDownloader = new HttpLinkDownloader(httpAdapter);
    }

    @Before
    public void initMockHttpResponse() {
        Request request = new Request.Builder().url("https://some-url.com").build();
        byte[] imageBytes = new byte[1];
        ResponseBody body = ResponseBody.create(imageBytes, MediaType.parse("image/png"));
        successResponse = new Response.Builder().request(request).message("").protocol(Protocol.HTTP_1_1).code(200).body(body).build();
        failResponse = new Response.Builder().request(request).message("").protocol(Protocol.HTTP_1_1).code(404).body(body).build();

    }


    @Test
    public void returnSuccessResponseForTheLink() throws IOException {

        Mockito.when(httpAdapter.execute(Mockito.anyString())).thenReturn(successResponse);
        String destinationPathString = ".";
        List<String> httpLinks = new ArrayList<>();
        httpLinks.add("https://something/hey.jpg");
        httpLinks.add("https://something/hello.jpg");
        Map<String, String> results = httpLinkDownloader.download(destinationPathString, httpLinks);
        Mockito.verify(httpAdapter, Mockito.times(2)).execute(Mockito.anyString());
        Assert.assertEquals(httpLinks.size(),
                results.entrySet().stream().filter(result -> "OK".equals(result.getValue())).collect(Collectors.toList()).size());

    }

    @Test
    public void returnKOForTheLink() throws IOException {

        Mockito.when(httpAdapter.execute(Mockito.anyString())).thenReturn(failResponse);
        String destinationPathString = ".";
        List<String> httpLinks = new ArrayList<>();
        httpLinks.add("https://games.mxdwn.com/wp-content/uploads/2020/11/doooom.jpg");
        Map<String, String> results = httpLinkDownloader.download(destinationPathString, httpLinks);
        Mockito.verify(httpAdapter, Mockito.times(1)).execute(Mockito.anyString());
        Assert.assertEquals(httpLinks.size(),
                results.entrySet().stream().filter(result -> "KO".equals(result.getValue())).collect(Collectors.toList()).size());

    }


}
