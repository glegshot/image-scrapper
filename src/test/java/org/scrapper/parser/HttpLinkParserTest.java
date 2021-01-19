package org.scrapper.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@RunWith(JUnit4.class)
public class HttpLinkParserTest {

    HttpLinkParser httpLinkParser;
    File sourceFile;
    File sourceFileEmpty;

    @Before
    public void initMocks() {
        httpLinkParser = new HttpLinkParser();
        httpLinkParser = Mockito.spy(httpLinkParser);
    }

    @Before
    public void initSourceFile() {
        URL fileURL = getClass().getClassLoader().getResource("sample.html");
        sourceFile = new File(fileURL.getPath());

        URL emptyFileURL = getClass().getClassLoader().getResource("sampleNoLinks.html");
        sourceFileEmpty = new File(emptyFileURL.getPath());
    }


    @Test
    public void scanTheHtmlFileForHttpLinksAndReturnAList() throws IOException {

        List<String> httpLinks = httpLinkParser.parse(sourceFile);
        Assert.assertEquals(httpLinks.size(), 2);
    }

    @Test
    public void scanTheHtmlFileWithNoHttpLinksAndReceiveEmptyString() throws IOException {

        List<String> httpLinks = httpLinkParser.parse(sourceFileEmpty);
        Assert.assertEquals(httpLinks.size(), 0);

    }


}
