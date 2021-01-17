package org.scrapper;


import org.scrapper.downloader.Downloader;
import org.scrapper.parser.HttpLinkParser;
import org.scrapper.parser.Parser;

public class ImageScrapperApplication {

    Parser httpLinkParser;
    Downloader httpLinkDownloader;

    public ImageScrapperApplication(Parser httpLinkParser, Downloader httpLinkDownloader){
        this.httpLinkParser = httpLinkParser;
        this.httpLinkDownloader = httpLinkDownloader;
    }

    public void getImages(){
        
    }

    public static void main(String[] args) {

    }

}
