package org.scrapper;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.*;
import org.scrapper.downloader.HttpLinkDownloader;
import org.scrapper.parser.HttpLinkParser;

import java.util.Map;
import java.util.stream.Collectors;

@RunWith(JUnit4.class)
public class ImageScrapperTest {

    @InjectMocks
    ImageScrapperApplication imageScrapperApplication;

    @Spy
    HttpLinkParser httpLinkParser;

    @Spy
    HttpLinkDownloader httpLinkDownloader;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void parseAndDownloadImagesAndReturnAllOkTest() {

        String sourcePath = "/sample.html";
        String destinationPath = "/";

        imageScrapperApplication = new ImageScrapperApplication(httpLinkParser, httpLinkDownloader);
        Map<String, String> results = imageScrapperApplication.getImages(sourcePath, destinationPath);

        Assert.assertEquals(results.size(),
                results.entrySet().stream().filter(result -> "OK".equals(result.getValue())).collect(Collectors.toList()).size());

        Mockito.verify(httpLinkParser, Mockito.times(1)).parse(Mockito.any());
        Mockito.verify(httpLinkDownloader, Mockito.times(1)).download(Mockito.any(), Mockito.anyList());


    }

}
