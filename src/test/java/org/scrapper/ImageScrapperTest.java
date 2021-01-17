package org.scrapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.scrapper.adapter.HttpClientAdapter;
import org.scrapper.file.FileOperations;
import org.scrapper.parser.Parser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@RunWith(JUnit4.class)
public class ImageScrapperTest {

    @Mock
    FileOperations fileOperations;

    @Mock
    Parser httpLinkParser;

    @Mock
    HttpClientAdapter httpClientAdapter;

    @InjectMocks
    ImageScrapperApplication imageScrapperApplication;


    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private String testHtmlString;

    @Before
    public void initFileReadData() throws IOException {

        Class clazz = ImageScrapperTest.class;
        InputStream inputStream = clazz.getResourceAsStream("/sample.html");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        int i;
        StringBuilder stringBuilder = new StringBuilder();
        while ((i = bufferedInputStream.read()) != -1) {
            stringBuilder.append((char) i);
        }

        testHtmlString = stringBuilder.toString();

    }

    private List<String> testHttpLink;

    @Before
    public void initTestHttpLinks() {

        List<String> httpLinks = new ArrayList<>();
        httpLinks.add("https://games.mxdwn.com/wp-content/uploads/2020/11/doooom.jpg");
        httpLinks.add("https://specials-images.forbesimg.com/imageserve/5dc5a8f1ca425400073c556a/960x0.jpg");
        testHttpLink = httpLinks;
    }

    @Before
    public void initHttpLinks() {


    }

    @Test
    public void parseAndDownloadImagesSuccessFullyTest() {

        Mockito.when(fileOperations.readFile()).thenReturn(testHtmlString);
        Mockito.when(httpLinkParser.parse()).thenReturn(testHttpLink);
        ImageScrapperApplication imageScrapperApplication = new ImageScrapperApplication();
        imageScrapperApplication.getImages();*/
        /*String inputFilePath = "./sample.html";
        String destinationDirectoryPath = "./output/";

        FileOperations fileOperations = new FileOperations(inputFilePath, destinationDirectoryPath);
        String inputData = fileOperations.readFile();
        Parser httpLinkParser = new HttpLinkParser(inputData);
        List<String> httpLinks = httpLinkParser.scrapeForLinks();
        HttpClientAdapter httpClientAdapter = new HttpClientAdapter(httpLinks);
        Map<String,String> results = httpClientAdapter.downloadFiles();*/


    }

}
