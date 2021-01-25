package org.scrapper;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.*;
import org.scrapper.adapter.HttpAdapter;
import org.scrapper.adapter.OkHttpAdapter;
import org.scrapper.downloader.Downloader;
import org.scrapper.downloader.HttpLinkDownloader;
import org.scrapper.exception.NotARegularFileException;
import org.scrapper.parser.HttpLinkParser;
import org.scrapper.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(JUnit4.class)
public class ImageScrapperTest {


    ImageScrapperApplication imageScrapperApplication;


    HttpLinkParser httpLinkParser;


    HttpLinkDownloader httpLinkDownloader;

    @Before
    public void initMocks() {

        HttpAdapter httpAdapter = new OkHttpAdapter();
        httpLinkParser = new HttpLinkParser();
        httpLinkParser = Mockito.spy(httpLinkParser);
        httpLinkDownloader = new HttpLinkDownloader(httpAdapter);
        httpLinkDownloader = Mockito.spy(httpLinkDownloader);
        imageScrapperApplication = new ImageScrapperApplication(httpLinkParser, httpLinkDownloader);
    }


    @Test
    public void parseAndDownloadImagesAndReturnAllOkTest() throws IOException {
        //acceptance test
        URL fileURL = getClass().getClassLoader().getResource("sample.html");
        String sourcePath = fileURL.getPath();
        String destinationPath = ".";

        imageScrapperApplication = new ImageScrapperApplication(httpLinkParser, httpLinkDownloader);
        Map<String, String> results = imageScrapperApplication.getImages(sourcePath, destinationPath);

        Assert.assertEquals(results.size(),
                results.entrySet().stream().filter(result -> "OK".equals(result.getValue())).collect(Collectors.toList()).size());

        Mockito.verify(httpLinkParser, Mockito.times(1)).parse(Mockito.any(File.class));
        Mockito.verify(httpLinkDownloader, Mockito.times(1)).download(Mockito.any(), Mockito.anyList());

    }


    @Test(expected = FileNotFoundException.class)
    public void getFileNotFoundExceptionForTheSourceFilePathProvided() throws IOException {

        String sourcePath = "/sample.html";
        String destinationPath = "/";

        imageScrapperApplication = new ImageScrapperApplication(httpLinkParser, httpLinkDownloader);
        imageScrapperApplication.getImages(sourcePath, destinationPath);


    }

    @Test(expected = NotARegularFileException.class)
    public void getNotARegularFileExceptionForSourceFilePathSetAsDirectoryFilePath() throws IOException {

        String sourcePath = "/";
        String destinationPath = "/";

        imageScrapperApplication = new ImageScrapperApplication(httpLinkParser, httpLinkDownloader);
        imageScrapperApplication.getImages(sourcePath, destinationPath);


    }


}
