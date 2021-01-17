package org.scrapper;


import org.scrapper.downloader.Downloader;
import org.scrapper.parser.HttpLinkParser;
import org.scrapper.parser.Parser;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ImageScrapperApplication {

    Parser httpLinkParser;
    Downloader httpLinkDownloader;

    public ImageScrapperApplication(Parser httpLinkParser, Downloader httpLinkDownloader) {
        this.httpLinkParser = httpLinkParser;
        this.httpLinkDownloader = httpLinkDownloader;
    }

    public Map<String, String> getImages(String sourcePath, String destinationPath) {

        File sourceFile = new File(sourcePath);
        File destinationDirectory = new File(destinationPath);
        List<String> httpLinks = httpLinkParser.parse(sourceFile);
        Map<String, String> results = httpLinkDownloader.download(destinationDirectory, httpLinks);
        return results;

    }

    public static void main(String[] args) {

    }

}
