package org.scrapper.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.net.URL;
import java.util.List;

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
    public void scanTheHtmlFileForHttpLinksAndReturnAList() {

        List<String> httpLinks = httpLinkParser.parse(sourceFile);
        Assert.assertEquals(httpLinks.size(), 2);
    }

    @Test
    public void scanTheHtmlFileWithNoHttpLinksAndReceiveEmptyString() {

        List<String> httpLinks = httpLinkParser.parse(sourceFileEmpty);
        Assert.assertEquals(httpLinks.size(), 0);

    }


}
