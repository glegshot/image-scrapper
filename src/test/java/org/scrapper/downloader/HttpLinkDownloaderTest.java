package org.scrapper.downloader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(JUnit4.class)
public class HttpLinkDownloaderTest {

    Downloader httpLinkDownloader;

    @Before
    public void initMocks() {
        httpLinkDownloader = new HttpLinkDownloader();
    }

    @Test
    public void returnSuccessResponseForTheLink() throws IOException {

        String destinationPathString = ".";
        List<String> httpLinks = new ArrayList<>();
        httpLinks.add("https://games.mxdwn.com/wp-content/uploads/2020/11/doooom.jpg");
        httpLinks.add("https://specials-images.forbesimg.com/imageserve/5dc5a8f1ca425400073c556a/960x0.jpg");
        Map<String, String> results = httpLinkDownloader.download(destinationPathString, httpLinks);
        Assert.assertEquals(httpLinks.size(),
                results.entrySet().stream().filter(result -> "OK".equals(result.getValue())).collect(Collectors.toList()).size());

    }

    @Test
    public void returnKOForOneLinkAndOKForAnotherLink() throws IOException {

        String destinationPathString = ".";
        List<String> httpLinks = new ArrayList<>();
        httpLinks.add("https://games.mxdwn.com/wp-content/uploads/2020/11/dooo.jpg");
        httpLinks.add("https://specials-images.forbesimg.com/imageserve/5dc5a8f1ca425400073c556a/960x0.jpg");
        Map<String, String> results = httpLinkDownloader.download(destinationPathString, httpLinks);
        Assert.assertEquals(1,
                results.entrySet().stream().filter(result -> "OK".equals(result.getValue())).collect(Collectors.toList()).size());
        Assert.assertEquals(1,
                results.entrySet().stream().filter(result -> "KO".equals(result.getValue())).collect(Collectors.toList()).size());


    }


}
