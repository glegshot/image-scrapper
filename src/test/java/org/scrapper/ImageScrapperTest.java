package org.scrapper;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.scrapper.downloader.Downloader;
import org.scrapper.parser.Parser;

@RunWith(JUnit4.class)
public class ImageScrapperTest {

    @InjectMocks
    ImageScrapperApplication imageScrapperApplication;

    @Mock
    Parser httpLinkParser;

    @Mock
    Downloader httpLinkDownloader;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseAndDownloadImagesSuccessFullyTest() {

        String sourcePath = "/sample.html";
        String destinationPath = "/";

        Mockito.verify(httpLinkParser, Mockito.times(1)).parse(Mockito.any());
        Mockito.verify(httpLinkDownloader, Mockito.times(1)).download(Mockito.anyString(),Mockito.anyList());

        imageScrapperApplication = new ImageScrapperApplication(httpLinkParser, httpLinkDownloader);
        imageScrapperApplication.getImages();

        //input and output path
        //send file to parser
        //save the links from parser
        //pass the links and filepath to downloader
        //return results and iterate to disaply final results

    }

}
